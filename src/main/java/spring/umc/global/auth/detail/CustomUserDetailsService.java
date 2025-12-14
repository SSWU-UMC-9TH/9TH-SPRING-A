package spring.umc.global.auth.detail;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import spring.umc.domain.member.entity.Member;
import spring.umc.domain.member.exeption.MemberException;
import spring.umc.domain.member.exeption.code.MemberErrorCode;
import spring.umc.domain.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(
            String username
    ) throws UsernameNotFoundException {
        // 검증할 Member 조회
        Member member = memberRepository.findByEmail(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("해당 이메일의 회원을 찾을 수 없습니다. email=" + username)
                );
        // CustomUserDetails 반환
        return new CustomUserDetails(member);
    }
}
