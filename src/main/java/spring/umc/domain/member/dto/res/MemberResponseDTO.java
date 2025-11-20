package spring.umc.domain.member.dto.res;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import spring.umc.domain.member.enums.Gender;
import spring.umc.domain.member.enums.Status;

import java.time.LocalDate;
import java.util.List;

public class MemberResponseDTO {

    /**
     * 단일 회원 조회
     */
    @Builder
    @Getter
    public static class MemberDTO {
        private Long memberId;
        private String name;
        private Gender gender;
        private String email;
        private String address;
        private Status status;
        private Integer point;
    }

    /**
     * 회원 목록 (Page) 조회
     */
    @Builder
    @Getter
    public static class MemberPageListDTO {
        private List<MemberDTO> memberList;
        private Integer listSize;
        private Integer totalPage;
        private Long totalElements;
        private Boolean isFirst;
        private Boolean isLast;
    }

    /**
     * 회원가입
     */
    @Builder
    public record JoinDTO(
            Long memberId,
            LocalDateTime createAt
    ){}
}