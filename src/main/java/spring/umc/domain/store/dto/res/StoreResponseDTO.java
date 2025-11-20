package spring.umc.domain.store.dto.res;

import lombok.Builder;
import lombok.Getter;
import java.util.List;

public class StoreResponseDTO {

    /**
     * 단일 가게 조회
     */
    @Builder
    @Getter
    public static class StoreDTO {
        private Long storeId;
        private String name;
        private String address;
        private Float score;
        private String regionName; // (Join)
    }

    /**
     * 가게 목록 (Page) 조회
     */
    @Builder
    @Getter
    public static class StorePageListDTO {
        private List<StoreDTO> storeList;
        private Integer listSize;
        private Integer totalPage;
        private Long totalElements;
        private Boolean isFirst;
        private Boolean isLast;
    }
}