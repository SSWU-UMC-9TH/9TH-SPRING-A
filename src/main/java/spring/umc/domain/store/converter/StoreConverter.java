package spring.umc.domain.store.converter;

import org.springframework.data.domain.Page;
import spring.umc.domain.store.dto.res.StoreResponseDTO;
import spring.umc.domain.store.entity.Store;

import java.util.List;
import java.util.stream.Collectors;

public class StoreConverter {

    /**
     * Entity -> DTO (단일)
     */
    public static StoreResponseDTO.StoreDTO toStoreDTO(Store store) {
        return StoreResponseDTO.StoreDTO.builder()
                .storeId(store.getStoreId())
                .name(store.getName())
                .address(store.getAddress())
                .score(store.getScore())
                .regionName(store.getRegion().getName()) // (Lazy Loading 주의 - Service에서 Join Fetch 필요)
                .build();
    }

    /**
     * Page<Entity> -> DTO (목록)
     */
    public static StoreResponseDTO.StorePageListDTO toStorePageListDTO(Page<Store> storePage) {
        List<StoreResponseDTO.StoreDTO> storeList = storePage.getContent().stream()
                .map(StoreConverter::toStoreDTO)
                .collect(Collectors.toList());

        return StoreResponseDTO.StorePageListDTO.builder()
                .storeList(storeList)
                .listSize(storeList.size())
                .totalPage(storePage.getTotalPages())
                .totalElements(storePage.getTotalElements())
                .isFirst(storePage.isFirst())
                .isLast(storePage.isLast())
                .build();
    }
}