package spring.umc.domain.mission.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.umc.domain.member.entity.Member;
import spring.umc.domain.member.repository.MemberRepository;
import spring.umc.domain.mission.entity.Mission;
import spring.umc.domain.mission.entity.mapping.MemberMission;
import spring.umc.domain.mission.enums.Status;
import spring.umc.domain.mission.repository.MemberMissionRepository;
import spring.umc.domain.mission.repository.MissionRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberMissionServiceImpl implements MemberMissionService {

    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;

    @Override
    @Transactional
    public MemberMission challengeMission(Long memberId, Long missionId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new RuntimeException("Mission not found"));

        memberMissionRepository.findByMemberAndMission(member, mission)
                .ifPresent(mm -> {
                    throw new RuntimeException("Already challenging mission");
                });

        MemberMission memberMission = MemberMission.builder()
                .member(member)
                .mission(mission)
                .status(Status.CHALLENGING)
                .build();

        return memberMissionRepository.save(memberMission);
    }

    /**
     * 내 도전 목록 보기 (페이징)
     */
    @Override
    public Page<Mission> getMyChallengingMissions(Long memberId, Pageable pageable) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        return memberMissionRepository.findChallengingMissionsByMember(member, pageable);
    }

    /**
     * 미션 완료하기 (트랜잭션)
     */
    @Override
    @Transactional
    public MemberMission completeMission(Long memberId, Long missionId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new RuntimeException("Mission not found"));

        // '도전 중'인 미션인지 검증
        MemberMission memberMission = memberMissionRepository.findByMemberAndMission(member, mission)
                .orElseThrow(() -> new RuntimeException("Mission not challenged"));

        // 이미 완료했거나, 도전 중이 아닌지 확인
        if (memberMission.getStatus() != Status.CHALLENGING) {
            throw new RuntimeException("Mission status is not CHALLENGING");
        }

        // 미션 상태를 'COMPLETED'로 변경
        MemberMission completedMemberMission = MemberMission.builder()
                .memberMissionId(memberMission.getMemberMissionId())
                .member(member)
                .mission(mission)
                .status(Status.COMPLETED)
                .build();

        member.addPoint(mission.getPoint());

        return memberMissionRepository.save(completedMemberMission);
    }
}