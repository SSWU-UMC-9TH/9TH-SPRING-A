package spring.umc.domain.mission.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import spring.umc.domain.mission.dto.MissionReqDTO;
import spring.umc.domain.mission.dto.MissionResDTO;
import spring.umc.domain.mission.service.MissionCommandService;
import spring.umc.global.apiPayload.ApiResponse;
import spring.umc.global.apiPayload.code.GeneralSuccessCode;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Tag(name = "Mission API", description = "미션 관련 API (생성 및 도전)")
public class MissionRestController {

    private final MissionCommandService missionCommandService;

    @PostMapping("/stores/{storeId}/missions")
    @Operation(summary = "가게 미션 추가 API", description = "특정 가게에 새로운 미션을 등록합니다.")
    @Parameters({
            @Parameter(name = "storeId", description = "미션을 추가할 가게 아이디")
    })
    public ApiResponse<MissionResDTO.AddResultDTO> addMission(
            @PathVariable(name = "storeId") Long storeId,
            @RequestBody @Valid MissionReqDTO.AddMissionDTO request
    ) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK,missionCommandService.addMission(storeId, request));
    }

    // 미션 도전하기
    // URL: POST /missions/{missionId}/challenges
    @PostMapping("/{missionId}/challenges")
    @Operation(summary = "미션 도전 API", description = "유저가 미션을 도전 중 상태로 추가합니다. (아직 유저 ID는 1로 고정)")
    @Parameters({
            @Parameter(name = "missionId", description = "도전할 미션의 아이디, path variable 입니다!")
    })
    public ApiResponse<MissionResDTO.ChallengeResultDTO> challengeMission(
            @PathVariable Long missionId
    ) {
        // 하드코딩 유저 ID 1L 사용
        return ApiResponse.onSuccess(GeneralSuccessCode.OK,missionCommandService.challengeMission(1L, missionId));
    }

    // 미션 완료처리하기
    // URL: POST /missions/{missionId}/challenges
    @PostMapping("/{missionId}/complete")
    @Operation(summary = "미션 도전 API", description = "유저가 미션을 도전 중 상태로 추가합니다. (아직 유저 ID는 1로 고정)")
    @Parameters({
            @Parameter(name = "missionId", description = "도전할 미션의 아이디, path variable 입니다!")
    })
    public ApiResponse<MissionResDTO.ChallengeResultDTO> completeMission(
            @PathVariable Long missionId
    ) {
        // 하드코딩 유저 ID 1L 사용
        return ApiResponse.onSuccess(GeneralSuccessCode.OK,missionCommandService.challengeMission(1L, missionId));
    }
}