package com.example.homeGym.admin.dto;

import com.example.homeGym.admin.entity.Settlement;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SettlementDto {
  private Long id;
  private Long instructorId;
  private Integer amount;
  private Settlement.SettlementState state;
  private LocalDateTime createdAt;
  private LocalDateTime completeTime;

  public static SettlementDto fromEntity(Settlement entity){
    SettlementDto.SettlementDtoBuilder builder = SettlementDto.builder()
            .id(entity.getId())
            .instructorId(entity.getInstructorId())
            .amount(entity.getAmount())
            .state(entity.getState())
            .createdAt(entity.getCreatedAt())
            .completeTime(entity.getCompleteTime());
    return builder.build();
  }
}
