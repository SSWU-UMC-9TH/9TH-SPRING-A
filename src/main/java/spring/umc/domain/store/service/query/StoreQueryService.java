package spring.umc.domain.store.service.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import spring.umc.domain.store.entity.Store;

public interface StoreQueryService {

    /**
     * 동적 쿼리(QueryDSL)를 이용한 가게 검색 (필터 포함)
     */
    Page<Store> searchStores(
            String region,
            Float score,
            String name,
            Pageable pageable
    );
}