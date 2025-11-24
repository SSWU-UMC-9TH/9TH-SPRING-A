package spring.umc.domain.store.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import spring.umc.domain.member.enums.Address;

public class StoreReqDTO {

    @Getter
    public static class AddStoreDTO {
        @NotBlank
        private String name;
        @NotNull
        private Address address; // 지역 (SEOUL, BUSAN 등)

        @NotBlank
        private String detailAddress; // 상세 주소
    }
}