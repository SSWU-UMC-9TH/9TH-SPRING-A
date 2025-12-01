package spring.umc.domain.mission.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import spring.umc.domain.mission.dto.MissionResDTO;
import spring.umc.domain.mission.exeption.code.MissionSuccessCode;
import spring.umc.domain.mission.service.query.MissionQueryService;
import spring.umc.global.annotation.ValidPage;
import spring.umc.global.apiPayload.ApiResponse;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/stores")
public class MissionController {

    private final MissionQueryService missionQueryService;

    @Operation(
            summary = "특정 가게의 미션 목록",
            description = "가게 ID 기준으로 해당 가게의 미션을 페이지 단위로 조회합니다. 한 페이지에 10개씩 반환됩니다."
    )
    @GetMapping("/{storeId}/missions")
    public ApiResponse<Page<MissionResDTO.StoreMissionSummary>> getStoreMissions(
            @Parameter(description = "가게 ID") @PathVariable Long storeId,
            @Parameter(description = "페이지 번호 (1부터 시작)", example = "1")
            @ValidPage @RequestParam(name = "page", defaultValue = "1") Integer page
    ) {
        return ApiResponse.onSuccess(
                MissionSuccessCode.CHALLENGED,
                missionQueryService.getStoreMissions(storeId, page)
        );
    }
}
