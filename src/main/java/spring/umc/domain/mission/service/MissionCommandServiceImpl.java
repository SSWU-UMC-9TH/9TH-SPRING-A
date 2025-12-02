package spring.umc.domain.mission.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.umc.domain.member.entity.UserMission;
import spring.umc.domain.member.entity.Member;
import spring.umc.domain.member.enums.MissionStatus;
import spring.umc.domain.member.repository.MemberRepository;
import spring.umc.domain.member.repository.UserMissionRepository;
import spring.umc.domain.mission.converter.MissionConverter;
import spring.umc.domain.mission.dto.MissionReqDTO;
import spring.umc.domain.mission.dto.MissionResDTO;
import spring.umc.domain.mission.entity.Mission;
import spring.umc.domain.mission.repository.MissionRepository;
import spring.umc.domain.store.entity.Store;
import spring.umc.domain.store.repository.StoreRepository;
import spring.umc.global.apiPayload.code.GeneralErrorCode;
import spring.umc.global.apiPayload.exception.GeneralException;

@Service
@RequiredArgsConstructor
public class MissionCommandServiceImpl implements MissionCommandService {

    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;
    private final UserMissionRepository userMissionRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public MissionResDTO.AddResultDTO addMission(Long storeId, MissionReqDTO.AddMissionDTO request) {

        // 미션을 추가할 가게가 존재하는지 확인
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.STORE_NOT_FOUND));

        // DTO -> Entity 변환 및 Store 연결
        Mission mission = MissionConverter.toMission(request, store);

        // DB저장
        missionRepository.save(mission);

        // 응답 DTO 반환
        return MissionConverter.toAddResultDTO(mission);
    }


    @Override
    @Transactional
    public MissionResDTO.ChallengeResultDTO challengeMission(Long userId, Long missionId) {

        // 유저, 미션 엔티티 조회 (존재 확인)
        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.MEMBER_NOT_FOUND));

        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.MISSION_NOT_FOUND));

        // 이미도전중인지 여부 확인
        if (userMissionRepository.existsByMemberIdAndMissionIdAndStatus(
                userId,
                missionId,
                MissionStatus.IN_PROGRESS //ENUM추가아직안함해야됨
        )) {
            throw new GeneralException(GeneralErrorCode.MISSION_ALREADY_CHALLENGING);
        }

        // UserMission 엔티티 생성
        UserMission userMission = UserMission.builder()
                .member(member)
                .mission(mission)
                .status(MissionStatus.IN_PROGRESS) // 시작 상태 설정 (Entity의 Default를 무시하고 명시하는 게 안전)
                .isFinished(false)
                .build();

        // 4. 저장
        userMissionRepository.save(userMission);

        // 5. 결과 반환
        return MissionConverter.toChallengeResultDTO(userMission);
    }

    @Override
    @Transactional
    public MissionResDTO.ChallengeResultDTO completeMission(Long userId, Long missionId) {

        // 유저, 미션 엔티티 조회 (존재 확인)
        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.MEMBER_NOT_FOUND));

        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.MISSION_NOT_FOUND));

        // 이미도전중인지 여부 확인
        if (userMissionRepository.existsByMemberIdAndMissionIdAndStatus(
                userId,
                missionId,
                MissionStatus.COMPLETED //ENUM추가아직안함해야됨
        )) {
            throw new GeneralException(GeneralErrorCode.MISSION_ALREADY_COMPLETE);
        }

        // UserMission 엔티티 생성
        UserMission userMission = UserMission.builder()
                .member(member)
                .mission(mission)
                .status(MissionStatus.COMPLETED) // 시작 상태 설정 (Entity의 Default를 무시하고 명시하는 게 안전)
                .isFinished(false)
                .build();

        // 4. 저장
        userMissionRepository.save(userMission);

        // 5. 결과 반환
        return MissionConverter.toChallengeResultDTO(userMission);
    }
}