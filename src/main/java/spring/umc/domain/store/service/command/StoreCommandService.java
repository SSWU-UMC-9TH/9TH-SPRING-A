package spring.umc.domain.store.service.command;

import spring.umc.domain.store.dto.req.StoreRequestDTO;
import spring.umc.domain.store.entity.Store;

public interface StoreCommandService {
    Store createStore(StoreRequestDTO.CreateStoreDTO request);
}
