package spring.umc.domain.mission.converter;

import spring.umc.domain.member.entity.Member;
import spring.umc.domain.mission.dto.UserMissionResDTO;
import spring.umc.domain.mission.entity.Mission;
import spring.umc.domain.mission.entity.mapping.UserMission;

public class UserMissionConverter {
    // Mission + Member -> UserMission 엔티티 생성
    public static UserMission toUserMission(
            Mission mission,
            Member member
    ) {
        return UserMission.builder()
                .mission(mission)
                .member(member)
                .build();
    }

    // UserMission 엔티티 -> 응답 DTO
    public static UserMissionResDTO.ChallengeDTO toChallengeDTO(
            UserMission userMission
    ) {
        return UserMissionResDTO.ChallengeDTO.builder()
                .userMissionId(userMission.getId())
                .memberId(userMission.getMember().getId())
                .missionId(userMission.getMission().getId())
                .storeId(userMission.getMission().getStore().getId())
                .isComplete(userMission.isComplete())
                .build();
    }
}
