package spring.umc.domain.mission.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import spring.umc.domain.mission.dto.UserMissionReqDTO;
import spring.umc.domain.mission.dto.UserMissionResDTO;
import spring.umc.domain.mission.exeption.code.MissionSuccessCode;
import spring.umc.domain.mission.service.command.UserMissionCommandService;
import spring.umc.domain.mission.service.query.MissionQueryService;
import spring.umc.global.annotation.ValidPage;
import spring.umc.global.apiPayload.ApiResponse;

@RestController
@RequiredArgsConstructor
public class UserMissionController {
    private final UserMissionCommandService userMissionCommandService;
    private final MissionQueryService userMissionQueryService;

    @Operation(
            summary = "내가 진행중인 미션 목록",
            description = "회원이 진행 중인(완료되지 않은) 미션 목록을 페이지 단위로 조회합니다. 한 페이지에 10개씩 반환됩니다."
    )
    @GetMapping("/{userId}/missions/ongoing")
    public ApiResponse<Page<UserMissionResDTO.OngoingMissionSummary>> getOngoingMissions(
            @Parameter(description = "회원 ID") @PathVariable("userId") Long userId,
            @Parameter(description = "페이지 번호 (1부터 시작)", example = "1")
            @ValidPage @RequestParam(name = "page", defaultValue = "1") Integer page
    ) {
        return ApiResponse.onSuccess(
                MissionSuccessCode.CHALLENGED,
                userMissionQueryService.getOngoingMissions(userId, page)
        );
    }
    // 가게 미션 도전
    @PostMapping("/{userId}/missionState")
    public ApiResponse<UserMissionResDTO.ChallengeDTO> challengeMission(
            @PathVariable("userId") Long userId,
            @RequestBody @Valid UserMissionReqDTO.ChallengeDTO dto
    ) {
        return ApiResponse.onSuccess(
                MissionSuccessCode.CHALLENGED,
                userMissionCommandService.challengeMission(userId, dto)
        );
    }
}
