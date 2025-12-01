package spring.umc.domain.mission.converter;

import spring.umc.domain.mission.dto.MissionResDTO;
import spring.umc.domain.mission.entity.Mission;
import spring.umc.domain.mission.entity.UserMission;
import spring.umc.domain.member.entity.User;

import java.util.List;

public class MissionConverter {

    public static UserMission toUserMission(User user, Mission mission) {
        return UserMission.builder()
                .user(user)
                .mission(mission)
                .status(UserMission.Status.in_progress)
                .assignedAt(java.time.LocalDateTime.now())
                .build();
    }

    public static MissionResDTO.ChallengeDTO toChallengeDTO(UserMission um) {
        return MissionResDTO.ChallengeDTO.builder()
                .userMissionId(um.getId())
                .userId(um.getUser().getId())
                .missionId(um.getMission().getId())
                .status(um.getStatus().name())
                .build();
    }

    /* 특정 가게 미션 목록 변환 */
    public static List<MissionResDTO.MissionListDTO> toMissionList(List<Mission> missions){
        return missions.stream()
                .map(m -> MissionResDTO.MissionListDTO.builder()
                        .missionId(m.getId())
                        .missionName(m.getName())
                        .rewardPoint(m.getRewardPoint())
                        .storeName(m.getStore().getName())
                        .build())
                .toList();
    }

    /* 진행중인 미션 조회 */
    public static List<MissionResDTO.MyMissionDTO> toMyMission(List<UserMission> missions){
        return missions.stream()
                .map(um -> MissionResDTO.MyMissionDTO.builder()
                        .userMissionId(um.getId())
                        .missionName(um.getMission().getName())
                        .storeName(um.getMission().getStore().getName())
                        .status(um.getStatus().name())
                        .assignedAt(um.getAssignedAt())
                        .build())
                .toList();
    }
}
