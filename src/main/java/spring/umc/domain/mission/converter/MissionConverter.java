package spring.umc.domain.mission.converter;

import spring.umc.domain.member.entity.UserMission;
import spring.umc.domain.mission.dto.MissionReqDTO;
import spring.umc.domain.mission.dto.MissionResDTO;
import spring.umc.domain.mission.entity.Mission;
import spring.umc.domain.store.entity.Store;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    // UserMission -> DTO (미션완료 결과 응답)
    public static MissionResDTO.CompleteResultDTO toCompleteResultDTO(UserMission userMission) {
        String statusString = userMission.getStatus() != null ? userMission.getStatus().toString() : "UNKNOWN";

        return MissionResDTO.CompleteResultDTO.builder()
                .userMissionId(userMission.getId())
                .missionId(userMission.getId()) // Mission ID 포함
                .status(statusString) // 완료된 상태 (COMPLETE) 포함
                .build();
    }

    public static MissionResDTO.UserMissionPreviewDTO toUserMissionPreviewDTO(UserMission userMission) {
        // Mission 엔티티는 UserMission에 포함되어 있을 것입니다.
        Mission mission = userMission.getMission();

        return MissionResDTO.UserMissionPreviewDTO.builder()
                .userMissionId(userMission.getId())
                .missionId(mission.getId())
                .missionName(mission.getMissionCondition())
                .dueDate(mission.getDeadline().atStartOfDay())
                .status(userMission.getStatus().toString())
                .build();
    }

    public static List<MissionResDTO.UserMissionPreviewDTO> toUserMissionPreviewListDTO(List<UserMission> userMissions) {
        return userMissions.stream()
                .map(MissionConverter::toUserMissionPreviewDTO)
                .collect(Collectors.toList());
    }
}