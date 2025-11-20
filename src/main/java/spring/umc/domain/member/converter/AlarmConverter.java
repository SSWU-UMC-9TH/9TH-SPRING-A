package spring.umc.domain.member.converter;

import org.springframework.data.domain.Page;
import spring.umc.domain.member.dto.res.AlarmResponseDTO;
import spring.umc.domain.member.entity.Alarm;

import java.util.List;
import java.util.stream.Collectors;

public class AlarmConverter {

    /**
     * Entity -> DTO (단일 조회용)
     */
    public static AlarmResponseDTO.AlarmDTO toAlarmDTO(Alarm alarm) {
        return AlarmResponseDTO.AlarmDTO.builder()
                .alarmId(alarm.getAlarmId())
                .title(alarm.getTitle())
                .content(alarm.getContent())
                .dtype(alarm.getDtype())
                .createdAt(alarm.getCreatedAt())
                .build();
    }

    /**
     * Page<Entity> -> DTO (목록)
     */
    public static AlarmResponseDTO.AlarmPageListDTO toAlarmPageListDTO(Page<Alarm> alarmPage) {
        List<AlarmResponseDTO.AlarmDTO> alarmList = alarmPage.getContent().stream()
                .map(AlarmConverter::toAlarmDTO)
                .collect(Collectors.toList());

        return AlarmResponseDTO.AlarmPageListDTO.builder()
                .alarmList(alarmList)
                .listSize(alarmList.size())
                .totalPage(alarmPage.getTotalPages())
                .totalElements(alarmPage.getTotalElements())
                .isFirst(alarmPage.isFirst())
                .isLast(alarmPage.isLast())
                .build();
    }
}