package spring.umc.domain.mission.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.umc.domain.mission.dto.MissionReqDTO;
import spring.umc.domain.mission.dto.MissionResDTO;
import spring.umc.domain.mission.service.command.MissionCommandService;
import spring.umc.domain.mission.service.query.MissionQueryService;
import spring.umc.global.annotation.OneBasedPageable;
import spring.umc.global.apiPayload.ApiResponse;
import spring.umc.global.apiPayload.code.GeneralSuccessCode;

@RestController
@RequestMapping("/api/missions")
@RequiredArgsConstructor
public class MissionController {

    private final MissionCommandService missionCommandService;
    private final MissionQueryService missionQueryService;

    /**
     * 미션 도전하기
     */
    @PostMapping("/challenge")
    public ResponseEntity<ApiResponse<MissionResDTO.ChallengeDTO>> challengeMission(
            @RequestBody MissionReqDTO.ChallengeDTO dto
    ) {
        MissionResDTO.ChallengeDTO result = missionCommandService.challengeMission(dto);
        return ResponseEntity.ok(ApiResponse.onSuccess(
                GeneralSuccessCode.OK, result
        ));
    }
    /**
     * 특정 가게의 미션 조회
     */
    @GetMapping("/store/{storeId}")
    @Operation(summary = "특정 가게의 미션 조회", description = "page=1부터 시작하여 10개씩 페이징됩니다.")
    public ApiResponse<Page<MissionResDTO.MissionListDTO>> getStoreMissions(
            @PathVariable Long storeId,
            @Parameter(description = "1-based page number") @OneBasedPageable Pageable pageable
    ) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                missionQueryService.getStoreMissions(storeId, pageable)
        );
    }

    /**
     * 내가 진행중인 미션 조회
     */
    @GetMapping("/my")
    @Operation(summary = "내가 진행중인 미션 목록 조회", description = "미션 상태 : in_progress / completed")
    public ApiResponse<Page<MissionResDTO.MyMissionDTO>> getMyMissions(
            @RequestParam Long userId,
            @Parameter(description = "1-based page number") @OneBasedPageable Pageable pageable
    ) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                missionQueryService.getMyMissions(userId, pageable)
        );
    }
}
