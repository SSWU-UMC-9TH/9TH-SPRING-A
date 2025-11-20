package spring.umc.domain.mission.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import spring.umc.domain.mission.converter.MissionConverter;
import spring.umc.domain.mission.dto.req.MissionRequestDTO;
import spring.umc.domain.mission.dto.res.MissionResponseDTO;
import spring.umc.domain.mission.entity.Mission;
import spring.umc.domain.mission.entity.mapping.MemberMission;
import spring.umc.domain.mission.service.MemberMissionService;
import spring.umc.domain.mission.service.MissionService;

import java.time.LocalDate;
import spring.umc.global.apiPayload.ApiResponse;
import spring.umc.global.apiPayload.code.GeneralSuccessCode;

@RestController
@RequestMapping("/missions")
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;
    private final MemberMissionService memberMissionService;

    /**
     * 미션 등록 (가게 주인)
     */
    @PostMapping
    public ApiResponse<MissionResponseDTO.CreateMissionResultDTO> createMission(
            @RequestParam Long storeId,
            @RequestBody MissionRequestDTO.CreateMissionDTO request) {
        Mission mission = missionService.createMission(
                storeId,
                request.getContent(),
                request.getPoint(),
                request.getDeadline(),
                request.getImageUrl()
        );
        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, MissionConverter.toCreateMissionResultDTO(mission));
    }

    /**
     * 미션 동적 검색 (QueryDSL)
     */
    @GetMapping("/search")
    public ApiResponse<MissionResponseDTO.MissionPageListDTO> searchMissions(
            @RequestParam(required = false) Long storeId,
            @RequestParam(required = false) Integer minPoint,
            @RequestParam(required = false) LocalDate deadlineBefore,
            @PageableDefault(size = 10) Pageable pageable) {
        Page<Mission> missionPage = missionService.searchMissions(storeId, minPoint, deadlineBefore, pageable);
        GeneralSuccessCode code = GeneralSuccessCode.OK;
        return ApiResponse.onSuccess(code, MissionConverter.toMissionPageListDTO(missionPage));
    }

    /**
     * 미션 도전하기 (사용자)
     */
    @PostMapping("/{missionId}/challenge")
    public ApiResponse<MissionResponseDTO.ChallengeMissionResultDTO> challengeMission(
            @PathVariable Long missionId,
            @RequestParam Long memberId) {
        MemberMission memberMission = memberMissionService.challengeMission(memberId, missionId);
        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, MissionConverter.toChallengeMissionResultDTO(memberMission));
    }

    /**
     * 미션 완료하기 (사용자)
     */
    @PatchMapping("/{missionId}/complete")
    public ApiResponse<MissionResponseDTO.ChallengeMissionResultDTO> completeMission(
            @PathVariable Long missionId,
            @RequestParam Long memberId) {
        MemberMission memberMission = memberMissionService.completeMission(memberId, missionId);
        GeneralSuccessCode code = GeneralSuccessCode.OK;
        return ApiResponse.onSuccess(code, MissionConverter.toChallengeMissionResultDTO(memberMission));
    }

    /**
     * 내 도전 목록 보기 (사용자)
     */
    @GetMapping("/my")
    public ApiResponse<MissionResponseDTO.MissionPageListDTO> getMyMissions(
            @RequestParam Long memberId,
            @PageableDefault(size = 10) Pageable pageable) {
        Page<Mission> missionPage = memberMissionService.getMyChallengingMissions(memberId, pageable);
        GeneralSuccessCode code = GeneralSuccessCode.OK;
        return ApiResponse.onSuccess(code, MissionConverter.toMissionPageListDTO(missionPage));
    }
}