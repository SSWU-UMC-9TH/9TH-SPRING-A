package spring.umc.domain.mission.converter;

import spring.umc.domain.mission.dto.MissionResDTO;
import spring.umc.domain.mission.entity.Mission;

import java.util.List;
import java.util.stream.Collectors;

public class MissionConverter {

    public static MissionResDTO.StoreMissionSummary toStoreMissionSummary(Mission mission) {
        return MissionResDTO.StoreMissionSummary.builder()
                .missionId(mission.getId())
                .storeName(mission.getStore().getName())
                .conditional(mission.getConditional())
                .point(mission.getPoint())
                .deadline(mission.getDeadline())
                .build();
    }

    public static List<MissionResDTO.StoreMissionSummary> toStoreMissionSummaryList(List<Mission> missions) {
        return missions.stream()
                .map(MissionConverter::toStoreMissionSummary)
                .collect(Collectors.toList());
    }
}
