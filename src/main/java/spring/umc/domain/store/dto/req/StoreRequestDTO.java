package spring.umc.domain.store.dto.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class StoreRequestDTO {
    @Getter
    public static class CreateStoreDTO {
        @NotBlank(message = "가게 이름은 필수값입니다.")
        private String name;

        @NotBlank(message = "가게 주소는 필수값입니다.")
        private String address;

        @NotNull(message = "지역 ID는 필수값입니다.")
        private Long regionId;
    }
}
