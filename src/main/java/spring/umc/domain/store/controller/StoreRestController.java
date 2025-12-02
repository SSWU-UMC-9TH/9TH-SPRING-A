package spring.umc.domain.store.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.umc.domain.store.dto.StoreReqDTO;
import spring.umc.domain.store.dto.StoreResDTO;
import spring.umc.domain.store.service.StoreCommandService;
import spring.umc.global.apiPayload.ApiResponse;
import spring.umc.global.apiPayload.code.GeneralSuccessCode;

@RestController
@RequiredArgsConstructor
//@RequestMapping("/api")
public class StoreRestController {

    private final StoreCommandService storeCommandService;

    @PostMapping("/stores")
    @Operation(summary = "가게 추가 API", description = "가게를 추가합니다. (지역 정보는 Body에 포함)")
    public ApiResponse<StoreResDTO.AddResultDTO> addStore(
            @RequestBody @Valid StoreReqDTO.AddStoreDTO request
    ) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, storeCommandService.addStore(request));
    }
}
