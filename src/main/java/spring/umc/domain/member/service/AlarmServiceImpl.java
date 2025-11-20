package spring.umc.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.umc.domain.member.entity.Alarm;
import spring.umc.domain.member.entity.Member;
import spring.umc.domain.member.enums.Dtype;
import spring.umc.domain.member.exception.MemberException;
import spring.umc.domain.member.exception.code.MemberErrorCode;
import spring.umc.domain.member.repository.AlarmRepository;
import spring.umc.domain.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AlarmServiceImpl implements AlarmService {

    private final AlarmRepository alarmRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public Alarm createAlarm(Long memberId, Dtype dtype, String title, String content) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        Alarm alarm = Alarm.builder()
                .member(member)
                .dtype(dtype)
                .title(title)
                .content(content)
                .build();

        return alarmRepository.save(alarm);
    }

    @Override
    public Page<Alarm> getMyAlarms(Long memberId, Pageable pageable) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        return alarmRepository.findByMemberOrderByCreatedAtDesc(member, pageable);
    }
}