package com.example.homeGym.user.service;

import com.example.homeGym.user.dto.ReviewDto;
import com.example.homeGym.user.dto.UserDto;
import com.example.homeGym.user.entity.Review;
import com.example.homeGym.user.entity.User;
import com.example.homeGym.user.repository.ReviewRepository;
import com.example.homeGym.user.utils.FileHandlerUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final FileHandlerUtils fileHandlerUtils;

    public List<ReviewDto> findByUserProgramIdAndUserId(Long userProgramId, Long userId){
//        if (reviewRepository.findByUserProgramIdAndUserId(userProgramId, userId).isPresent()){
//
//            return ReviewDto.fromEntity(reviewRepository.findByUserProgramIdAndUserId(userProgramId, userId));
//        }
//        return ReviewDto.fromEntity(reviewRepository.findByUserProgramIdAndUserId(userProgramId, userId));
        List<ReviewDto> reviewDtos = new ArrayList<>();
        for (Review review : reviewRepository.findAllByUserProgramIdAndUserId(userProgramId, userId)){
            reviewDtos.add(ReviewDto.fromEntity(review));
        }
        return reviewDtos;

    }

    public ReviewDto createReview(Long userId, Long userProgramId, List<MultipartFile> images) throws IOException{
        List<String> imagePaths = new ArrayList<>();
        int count = 0;
        for (MultipartFile image :
                images) {
            if (image.getSize() != 0){
                String imgPath = fileHandlerUtils.saveFile("review",
                        String.format("review_image_user_%d_program_%d_%d", userId, userProgramId, count), image);
                imagePaths.add(imgPath);
                count ++;
            }
        }

        Review review = new Review();
        review.setMemo("test중입니다");
        review.setStars(4);
        review.setUserId(userId);
        review.setUserProgramId(userProgramId);
        review.setImageUrl(imagePaths);
        return ReviewDto.fromEntity(reviewRepository.save(review));
    }

    @Transactional
    public void deleteReview(Long reviewId){
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        //리뷰에 연관된 이미지 파일 경로를 가져오기.
        List<String> imagePaths = review.getImageUrl();

        //리뷰 삭제
        reviewRepository.delete(review);

        //이미지 파일이 존재하면 삭제
        if (!imagePaths.isEmpty()){
            for (String imagePath :
                    imagePaths) {
                String mediaPath = "media/";
                String fullPath = mediaPath + imagePath.replace("/static/", "");
                try{
                    Files.deleteIfExists(Path.of(fullPath));
                }catch (IOException e){
                    log.error(e.getMessage());
                    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        }
    }
}
