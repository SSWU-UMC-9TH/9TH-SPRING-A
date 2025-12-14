package spring.umc.domain.mission.converter;

import spring.umc.domain.member.entity.Member;
import spring.umc.domain.mission.dto.UserMissionResDTO;
import spring.umc.domain.mission.entity.Mission;
import spring.umc.domain.mission.entity.mapping.UserMission;

import java.util.List;
import java.util.stream.Collectors;

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

    public static UserMissionResDTO.OngoingMissionSummary toOngoingSummary(UserMission userMission) {
        return UserMissionResDTO.OngoingMissionSummary.builder()
                .userMissionId(userMission.getId())
                .missionId(userMission.getMission().getId())
                .storeName(userMission.getMission().getStore().getName())
                .conditional(userMission.getMission().getConditional())
                .point(userMission.getMission().getPoint())
                .deadline(userMission.getMission().getDeadline())
                .build();
    }

    public static List<UserMissionResDTO.OngoingMissionSummary> toOngoingSummaryList(List<UserMission> userMissions) {
        return userMissions.stream()
                .map(UserMissionConverter::toOngoingSummary)
                .collect(Collectors.toList());
    }
}
