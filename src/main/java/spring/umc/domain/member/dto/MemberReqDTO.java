package spring.umc.domain.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;
import spring.umc.domain.member.enums.Address;
import spring.umc.domain.member.enums.Gender;
import spring.umc.global.annotation.ExistFoods;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {
    public record JoinDTO(
            String name,
            Gender gender,
            LocalDate birth,
            Address address, //음
            String specAddress,
            @Email
            String email,
            @NotBlank
            String password,
            @ExistFoods
            List<Long> preferCategory
    ){}

    // 로그인
    public record LoginDTO(
            @NotBlank
            String email,
            @NotBlank
            String password
    ){}
}
