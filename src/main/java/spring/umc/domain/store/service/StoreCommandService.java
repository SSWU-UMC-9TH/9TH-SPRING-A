package spring.umc.domain.store.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.umc.domain.store.converter.StoreConverter;
import spring.umc.domain.store.dto.StoreReqDTO;
import spring.umc.domain.store.dto.StoreResDTO;
import spring.umc.domain.store.entity.Store;
import spring.umc.domain.store.repository.StoreRepository;

@Service
@RequiredArgsConstructor
public class StoreCommandService {

    private final StoreRepository storeRepository;


    @Transactional
    public StoreResDTO.AddResultDTO addStore(StoreReqDTO.AddStoreDTO request) {


        Store store = StoreConverter.toEntity(request);


        storeRepository.save(store);

        return StoreConverter.toAddResultDTO(store);
    }
}