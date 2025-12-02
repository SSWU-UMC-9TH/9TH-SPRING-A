package spring.umc.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MyPageInfoDTO { //5주차 피드백 수정
    private String name;
    private String email;
    private String phoneNum;
    private Long userId;
    private Long totalPoints;
}