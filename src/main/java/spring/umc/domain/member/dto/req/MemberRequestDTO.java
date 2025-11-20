package spring.umc.domain.member.dto.req;

import lombok.Getter;
import java.util.List;

public class MemberRequestDTO {

    @Getter
    public static class UpdatePreferFoodsDTO {
        private List<Long> categoryIds;
    }
}