package spring.umc.domain.mission.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.umc.domain.mission.dto.MissionReqDTO;
import spring.umc.domain.mission.dto.MissionResDTO;
import spring.umc.domain.mission.service.command.MissionCommandService;
import spring.umc.global.apiPayload.ApiResponse;
import spring.umc.global.apiPayload.code.GeneralSuccessCode;

@RestController
@RequestMapping("/api/missions")
@RequiredArgsConstructor
public class MissionController {

    private final MissionCommandService missionCommandService;

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
}
