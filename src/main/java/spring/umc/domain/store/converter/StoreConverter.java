package spring.umc.domain.store.converter;

import spring.umc.domain.store.dto.StoreReqDTO;
import spring.umc.domain.store.dto.StoreResDTO;
import spring.umc.domain.store.entity.Store;

import java.time.LocalDateTime;

public class StoreConverter {

    public static StoreResDTO.AddResultDTO toAddResultDTO(Store store) {
        return StoreResDTO.AddResultDTO.builder()
                .storeId(store.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Store toEntity(StoreReqDTO.AddStoreDTO request) {
        return Store.builder()
                .name(request.getName())
                .address(request.getAddress())
                .detailAddress(request.getDetailAddress())
                .build();
    }
}