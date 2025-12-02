package spring.umc.domain.member.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MissionHistoryDto {
    private String storeName;
    private LocalDateTime deadline;
    private String missionCondition;
    private Integer rewardPoint;

    public MissionHistoryDto(String storeName, LocalDateTime deadline, String  missionCondition, Integer rewardPoint){
        this.storeName=storeName;
        this.deadline=deadline;
        this.missionCondition=missionCondition;
        this.rewardPoint=rewardPoint;
    }
}
