package spring.umc.domain.member.dto;

import spring.umc.domain.member.enums.Gender;
import spring.umc.domain.store.enums.Address;
import spring.umc.global.annotation.ExistFoods;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {
    public record JoinDTO(
            String name,
            Gender gender,
            LocalDate birth,
            Address address,
            String specAddress,
            @ExistFoods
            List<Long> preferCategory
    ){}
}
