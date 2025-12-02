package spring.umc.domain.mission.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

import java.util.List;

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

        // 1. 필요한 엔티티 조회 (Member와 Mission)
        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.MEMBER_NOT_FOUND));

        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.MISSION_NOT_FOUND));


        // 2. UserMission 엔티티를 조회 (INSERT 대신 UPDATE를 위한 대상 찾기)
        UserMission userMission = userMissionRepository.findByMemberIdAndMissionId(userId, missionId)
        .orElseThrow(() -> new GeneralException(GeneralErrorCode.MISSION_NOT_FOUND));
        // 미션을 시작하지 않았다면 (IN_PROGRESS 레코드가 없다면) 예외 처리


        // 3. (정합성) 이미 완료 상태인지 확인
        if (userMission.getStatus() == MissionStatus.COMPLETED) {
            throw new GeneralException(GeneralErrorCode.MISSION_ALREADY_COMPLETE);
        }



        userMission.updateStatusToComplete();
        // userMission.setStatus(MissionStatus.COMPLETED);
        // userMission.setFinished(true);

        return MissionConverter.toChallengeResultDTO(userMission);
    }

    public Page<MissionResDTO.UserMissionPreviewDTO> getInProgressMissions(Integer page) {

        // 1. Pageable 객체 생성
        // 클라이언트가 1부터 시작하는 페이지 번호를 보냈다면, 0부터 시작하는 인덱스로 변환합니다.
        int pageIndex = (page == null || page < 1) ? 0 : page - 1;

        // PageRequest.of(페이지 인덱스, 페이지 크기, 정렬 기준)
        Pageable pageable = PageRequest.of(pageIndex, 5);

        // 2. Repository 호출: Pageable 객체 전달
        Page<UserMission> userMissionsPage = userMissionRepository.findAllByMemberIdAndStatus(
                1L,
                MissionStatus.IN_PROGRESS,
                pageable
        );

        // 3. Page<Entity>를 Page<DTO>로 변환하여 반환
        // Page 객체의 map 메서드를 사용하면 쉽게 DTO로 변환할 수 있습니다.
        return userMissionsPage.map(MissionConverter::toUserMissionPreviewDTO);
    }
}