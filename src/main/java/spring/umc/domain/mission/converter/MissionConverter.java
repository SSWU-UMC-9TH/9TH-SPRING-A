package spring.umc.domain.mission.converter;

import spring.umc.domain.mission.dto.MissionResDTO;
import spring.umc.domain.mission.entity.Mission;
import spring.umc.domain.mission.entity.UserMission;
import spring.umc.domain.member.entity.User;

public class MissionConverter {

    public static UserMission toUserMission(User user, Mission mission) {
        return UserMission.builder()
                .user(user)
                .mission(mission)
                .status(UserMission.Status.in_progress)
                .assignedAt(java.time.LocalDateTime.now())
                .build();
    }

    public static MissionResDTO.ChallengeDTO toChallengeDTO(UserMission userMission) {
        return MissionResDTO.ChallengeDTO.builder()
                .userMissionId(userMission.getId())
                .userId(userMission.getUser().getId())
                .missionId(userMission.getMission().getId())
                .status(userMission.getStatus().name())
                .build();
    }
}
