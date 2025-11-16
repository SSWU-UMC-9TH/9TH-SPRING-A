package spring.umc.domain.store.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.umc.domain.store.converter.StoreConverter;
import spring.umc.domain.store.dto.req.StoreRequestDTO;
import spring.umc.domain.store.entity.Region;
import spring.umc.domain.store.entity.Store;
import spring.umc.domain.store.exception.StoreException;
import spring.umc.domain.store.exception.code.StoreErrorCode;
import spring.umc.domain.store.repository.RegionRepository;
import spring.umc.domain.store.repository.StoreRepository;

@Service
@RequiredArgsConstructor
public class StoreCommandServiceImpl implements StoreCommandService {
    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;

    @Override
    @Transactional
    public Store createStore(StoreRequestDTO.CreateStoreDTO request) {
        Region region = regionRepository.findById(request.getRegionId())
                .orElseThrow(() -> new StoreException(StoreErrorCode.REGION_NOT_FOUND));

        Store newStore = StoreConverter.toStore(request, region);

        return storeRepository.save(newStore);
    }
}
