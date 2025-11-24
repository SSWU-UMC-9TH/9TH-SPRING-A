package spring.umc.domain.mission.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.umc.domain.member.entity.User;
import spring.umc.domain.member.repository.MemberRepository;
import spring.umc.domain.mission.converter.MissionConverter;
import spring.umc.domain.mission.dto.MissionReqDTO;
import spring.umc.domain.mission.dto.MissionResDTO;
import spring.umc.domain.mission.entity.Mission;
import spring.umc.domain.mission.entity.UserMission;
import spring.umc.domain.mission.exception.code.MissionErrorCode;
import spring.umc.domain.mission.exception.MissionException;
import spring.umc.domain.mission.repository.MemberMissionRepository;
import spring.umc.domain.mission.repository.MissionRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class MissionCommandServiceImpl implements MissionCommandService {

    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Override
    public MissionResDTO.ChallengeDTO challengeMission(MissionReqDTO.ChallengeDTO dto) {

        // 유저 검증
        User user = memberRepository.findById(dto.userId())
                .orElseThrow(() -> new MissionException(MissionErrorCode.USER_NOT_FOUND));

        // 미션 검증
        Mission mission = missionRepository.findById(dto.missionId())
                .orElseThrow(() -> new MissionException(MissionErrorCode.MISSION_NOT_FOUND));

        // 이미 도전/완료한 미션인지 체크
        boolean inProgress = memberMissionRepository
                .existsByUserIdAndMissionIdAndStatus(
                        dto.userId(),
                        dto.missionId(),
                        UserMission.Status.in_progress.name()
                );

        if (inProgress) {
            throw new MissionException(MissionErrorCode.ALREADY_IN_PROGRESS);
        }

        boolean completed = memberMissionRepository
                .existsByUserIdAndMissionIdAndStatus(
                        dto.userId(),
                        dto.missionId(),
                        UserMission.Status.completed.name()
                );

        if (completed) {
            throw new MissionException(MissionErrorCode.ALREADY_COMPLETED);
        }

        // 새 UserMission 생성
        UserMission userMission = MissionConverter.toUserMission(user, mission);

        // 저장
        memberMissionRepository.save(userMission);

        // 응답 DTO 변환
        return MissionConverter.toChallengeDTO(userMission);
    }
}
