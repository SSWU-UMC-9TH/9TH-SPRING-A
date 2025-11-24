package spring.umc.domain.mission.converter;

import spring.umc.domain.member.entity.UserMission;
import spring.umc.domain.mission.dto.MissionReqDTO;
import spring.umc.domain.mission.dto.MissionResDTO;
import spring.umc.domain.mission.entity.Mission;
import spring.umc.domain.store.entity.Store;

import java.time.LocalDateTime;

public class MissionConverter {

    // DTO -> Entity (미션 생성)
    public static Mission toMission(MissionReqDTO.AddMissionDTO request, Store store) {
        return Mission.builder()
                .point(request.getPointReward()) // DTO의 pointReward -> Entity의 point
                .deadline(request.getDeadline())
                .missionCondition(request.getMissionCondition())
                .store(store)
                .build();
    }
    // Entity -> DTO (생성 결과 응답)
    public static MissionResDTO.AddResultDTO toAddResultDTO(Mission mission) {
        return MissionResDTO.AddResultDTO.builder()
                .missionId(mission.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    // UserMission -> DTO (도전 결과 응답)
    public static MissionResDTO.ChallengeResultDTO toChallengeResultDTO(UserMission userMission) {
        return MissionResDTO.ChallengeResultDTO.builder()
                .userMissionId(userMission.getId())
                .createdAt(userMission.getCreatedAt())
                .build();
    }
}