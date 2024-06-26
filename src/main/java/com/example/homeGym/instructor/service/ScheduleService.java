package com.example.homeGym.instructor.service;

import com.example.homeGym.instructor.dto.ScheduleDto;
import com.example.homeGym.instructor.entity.Instructor;
import com.example.homeGym.instructor.entity.Schedule;
import com.example.homeGym.instructor.entity.Time;
import com.example.homeGym.instructor.entity.Week;
import com.example.homeGym.instructor.repository.ScheduleRepository;
import com.example.homeGym.common.util.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final AuthenticationFacade facade;

    public List<ScheduleDto> getAllSchedulesByInstructorId(Long instructorId) {
        List<Schedule> schedules = scheduleRepository.findAllByInstructorId(instructorId);
        return schedules.stream()
                .map(ScheduleDto::fromEntity)
                .collect(Collectors.toList());
    }

    public List<ScheduleDto> getAllSchedules(Long instructorId) {
        List<Schedule> schedules = scheduleRepository.findAllByInstructorId(instructorId);
        return schedules.stream()
                .map(ScheduleDto::fromEntity)
                .collect(Collectors.toList());
    }


    @Transactional
    public void saveSchedules(List<Schedule> schedules, Long instructorId, String instructorName) {
        // 각 스케줄에 instructorId와 instructorName 설정
        schedules.forEach(schedule -> {
            schedule.setInstructorId(instructorId);
            schedule.setInstructorName(instructorName);
        });
        // 스케줄 저장
        scheduleRepository.saveAll(schedules);
    }

    @Transactional
    public void deleteCanceledSchedules(List<ScheduleDto> canceledSchedules) {
        for (ScheduleDto dto : canceledSchedules) {
            Long id = dto.getId(); // ID 추출
            if (id != null) {
                scheduleRepository.deleteById(id); // ID 기반으로 삭제
                log.info("Deleted schedule: {}", id);
            } else {
                log.warn("Cannot delete schedule without ID: {}", dto);
            }
        }
    }

    @Transactional
    public void deleteSchedule(Long scheduleId) {
        Schedule schedule = findScheduleById(scheduleId);
        scheduleRepository.delete(schedule);

        log.info("Deleted schedule: {}", scheduleId);
    }

    private Schedule findScheduleById(Long scheduleId) {
        return scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> {
                    log.warn("Schedule not found: {}", scheduleId);
                    return new IllegalArgumentException("Schedule not found");
                });
    }

    //TODO test
    @Transactional
    public ScheduleDto saveSchedule(String week, String time) {
        Instructor currentInstructor = facade.getCurrentInstructor();

        Schedule schedule = Schedule.builder()
                .week(week)
                .time(time)

                .instructorName(currentInstructor.getName())
                .instructorId(currentInstructor.getId())
                .build();

        log.info("Creating new schedule for instructor: {}", currentInstructor.getId());

        return ScheduleDto.fromEntity(scheduleRepository.save(schedule));
    }
}
