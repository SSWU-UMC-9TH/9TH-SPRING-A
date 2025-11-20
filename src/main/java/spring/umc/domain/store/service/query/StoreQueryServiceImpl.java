package spring.umc.domain.store.service.query;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.umc.domain.store.entity.Store;
import spring.umc.domain.store.repository.StoreRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreQueryServiceImpl implements StoreQueryService {

    private final StoreRepository storeRepository;

    @Override
    public Page<Store> searchStores(
            String region, Float score, String name, Pageable pageable
    ) {
        return storeRepository.searchStoresByFilter(region, score, name, pageable);
    }
}