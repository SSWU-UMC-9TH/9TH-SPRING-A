package spring.umc.domain.store.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spring.umc.domain.store.converter.StoreConverter;
import spring.umc.domain.store.dto.req.StoreRequestDTO;
import spring.umc.domain.store.dto.res.StoreResponseDTO;
import spring.umc.domain.store.dto.res.StoreResponseDTO.StorePageListDTO;
import spring.umc.domain.store.entity.Store;
import spring.umc.domain.store.service.command.StoreCommandService;
import spring.umc.domain.store.service.command.StoreCommandServiceImpl;
import spring.umc.domain.store.service.query.StoreQueryService;
import spring.umc.global.apiPayload.ApiResponse;
import spring.umc.global.apiPayload.code.GeneralSuccessCode;

@RestController
@RequestMapping("/stores")
@RequiredArgsConstructor
public class StoreController {

    private final StoreQueryService storeQueryService;
    private final StoreCommandService storeCommandService;

    /**
     * 가게 동적 검색 (QueryDSL)
     */
    @GetMapping("/search")
    public ApiResponse<StorePageListDTO> searchStores(
            @RequestParam(required = false) String regionName,
            @RequestParam(required = false) Float minScore,
            @RequestParam(required = false) String nameKeyword,
            @PageableDefault(size = 10, sort = "createdAt") Pageable pageable) {
        Page<Store> storePage = storeQueryService.searchStores(regionName, minScore, nameKeyword, pageable);
        GeneralSuccessCode code = GeneralSuccessCode.OK;
        return ApiResponse.onSuccess(code, StoreConverter.toStorePageListDTO(storePage));
    }

    /**
     * 특정 지역에 가게 추가하기 API
     */
    @PostMapping("/")
    public ApiResponse<StoreResponseDTO.CreateStoreResultDTO> createStore(
            @RequestBody @Valid StoreRequestDTO.CreateStoreDTO request
    ) {
        Store store = storeCommandService.createStore(request);
        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, StoreConverter.toCreateStoreResultDTO(store));
    }
}