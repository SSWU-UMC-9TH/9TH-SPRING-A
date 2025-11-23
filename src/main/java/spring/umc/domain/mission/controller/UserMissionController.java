package spring.umc.domain.mission.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import spring.umc.domain.mission.dto.UserMissionReqDTO;
import spring.umc.domain.mission.dto.UserMissionResDTO;
import spring.umc.domain.mission.exeption.code.MissionSuccessCode;
import spring.umc.domain.mission.service.command.UserMissionCommandService;
import spring.umc.global.apiPayload.ApiResponse;

@RestController
@RequiredArgsConstructor
public class UserMissionController {
    private final UserMissionCommandService userMissionCommandService;

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
