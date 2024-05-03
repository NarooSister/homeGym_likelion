package com.example.homeGym.order.dto;

import com.example.homeGym.instructor.entity.Program;
import com.example.homeGym.order.entity.ProgramOrder;
import com.example.homeGym.user.dto.UserDto;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProgramOrderDto {
    private Long id;
    private Long userId;

    private Long programId;

    private String programTitle;

    private Long amount;

    private String tossPaymentKey;
    private String tossOrderId;

    private UserDto userDto;
    private Program program;

    private String week;
    private String time;
    private String count;

    public static ProgramOrderDto fromEntity(ProgramOrder order) {
        return ProgramOrderDto.builder()
                .id(order.getOrderId())
                .programTitle(order.getProgram().getTitle())
                .userId(order.getUser().getId())
                .programId(order.getProgram().getId())
                .amount(order.getAmount())
                .tossPaymentKey(order.getTossPaymentKey())
                .tossOrderId(order.getTossOrderId())
                .build();
    }


}
