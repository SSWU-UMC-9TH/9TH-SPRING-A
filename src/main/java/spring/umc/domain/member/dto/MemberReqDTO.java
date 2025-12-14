package spring.umc.domain.member.dto;

import jakarta.validation.constraints.NotBlank;
import spring.umc.domain.member.enums.Gender;
import spring.umc.domain.store.enums.Address;
import spring.umc.global.annotation.ExistFoods;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {
    public record JoinDTO(
            String name,
            String email, // 추가된 속성
            @NotBlank
            String password, // 추가된 속성
            Gender gender,
            LocalDate birth,
            Address address,
            String specAddress,
            @ExistFoods
            List<Long> preferCategory
    ){}
}
