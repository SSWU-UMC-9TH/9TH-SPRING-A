package spring.umc.domain.member.dto.req;

import java.time.LocalDate;
import lombok.Getter;
import java.util.List;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails.Address;
import spring.umc.domain.member.enums.Gender;
import spring.umc.global.annotation.ExistFoods;

public class MemberRequestDTO {

    @Getter
    public static class UpdatePreferFoodsDTO {
        private List<Long> categoryIds;
    }

    /**
     * 회원가입
     */
    public record JoinDTO(
            String name,
            Gender gender,
            Integer age,
            String email,
            String address,
            String phoneNumber,
            @ExistFoods
            List<Long> preferFoodCategory
    ){}
}