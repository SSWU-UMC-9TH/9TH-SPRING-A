package spring.umc.domain.mission.converter;

import org.springframework.data.domain.Page;
import spring.umc.domain.mission.dto.res.MissionResponseDTO;
import spring.umc.domain.mission.entity.Mission;
import spring.umc.domain.mission.entity.mapping.MemberMission;

import java.util.List;
import java.util.stream.Collectors;

public class MissionConverter {

    /**
     * 미션 생성 결과 DTO
     */
    public static MissionResponseDTO.CreateMissionResultDTO toCreateMissionResultDTO(Mission mission) {
        return MissionResponseDTO.CreateMissionResultDTO.builder()
                .missionId(mission.getMissionId())
                .storeName(mission.getStore().getName())
                .createdAt(mission.getCreatedAt())
                .build();
    }

    /**
     * 미션 도전 결과 DTO
     */
    public static MissionResponseDTO.ChallengeMissionResultDTO toChallengeMissionResultDTO(MemberMission memberMission) {
        return MissionResponseDTO.ChallengeMissionResultDTO.builder()
                .memberMissionId(memberMission.getMemberMissionId())
                .memberName(memberMission.getMember().getName())
                .missionContent(memberMission.getMission().getContent())
                .status(memberMission.getStatus())
                .build();
    }

    /**
     * Entity -> DTO (단일 조회용)
     */
    public static MissionResponseDTO.MissionDTO toMissionDTO(Mission mission) {
        return MissionResponseDTO.MissionDTO.builder()
                .missionId(mission.getMissionId())
                .storeName(mission.getStore().getName())
                .content(mission.getContent())
                .point(mission.getPoint())
                .deadline(mission.getDeadline())
                .build();
    }

    /**
     * Page<Entity> -> DTO (목록)
     */
    public static MissionResponseDTO.MissionPageListDTO toMissionPageListDTO(Page<Mission> missionPage) {
        List<MissionResponseDTO.MissionDTO> missionList = missionPage.getContent().stream()
                .map(MissionConverter::toMissionDTO)
                .collect(Collectors.toList());

        return MissionResponseDTO.MissionPageListDTO.builder()
                .missionList(missionList)
                .listSize(missionList.size())
                .totalPage(missionPage.getTotalPages())
                .totalElements(missionPage.getTotalElements())
                .isFirst(missionPage.isFirst())
                .isLast(missionPage.isLast())
                .build();
    }
}