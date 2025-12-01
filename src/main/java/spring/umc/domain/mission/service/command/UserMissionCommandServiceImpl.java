package spring.umc.domain.mission.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.umc.domain.member.entity.Member;
import spring.umc.domain.member.exeption.MemberException;
import spring.umc.domain.member.exeption.code.MemberErrorCode;
import spring.umc.domain.member.repository.MemberRepository;
import spring.umc.domain.mission.converter.UserMissionConverter;
import spring.umc.domain.mission.dto.UserMissionReqDTO;
import spring.umc.domain.mission.dto.UserMissionResDTO;
import spring.umc.domain.mission.entity.Mission;
import spring.umc.domain.mission.entity.mapping.UserMission;
import spring.umc.domain.mission.exeption.MissionException;
import spring.umc.domain.mission.exeption.code.MissionErrorCode;
import spring.umc.domain.mission.repository.MissionRepository;
import spring.umc.domain.mission.repository.UserMissionRepository;

@Service
@RequiredArgsConstructor
public class UserMissionCommandServiceImpl implements UserMissionCommandService {

    private final UserMissionRepository userMissionRepository;
    private final MissionRepository missionRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public UserMissionResDTO.ChallengeDTO challengeMission(
            Long userId,
            UserMissionReqDTO.ChallengeDTO dto
    ) {
        // 1. 회원 검증
        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        // 2. 미션 검증
        Mission mission = missionRepository.findById(dto.missionId())
                .orElseThrow(() -> new MissionException(MissionErrorCode.NOT_FOUND));

        // 3. 이미 도전 중인지 검사
        if (userMissionRepository.existsByMemberAndMission(member, mission)) {
            throw new MissionException(MissionErrorCode.ALREADY_CHALLENGED);
        }

        // 4. UserMission 엔티티 생성
        UserMission userMission = UserMissionConverter.toUserMission(mission, member);

        // 5. DB 저장
        userMissionRepository.save(userMission);

        // 6. 응답 DTO 반환
        return UserMissionConverter.toChallengeDTO(userMission);
    }
}
