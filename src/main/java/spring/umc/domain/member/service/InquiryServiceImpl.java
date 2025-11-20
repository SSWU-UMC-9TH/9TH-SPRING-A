package spring.umc.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.umc.domain.member.entity.Inquiry;
import spring.umc.domain.member.entity.InquiryImage;
import spring.umc.domain.member.entity.Member;
import spring.umc.domain.member.exception.MemberException;
import spring.umc.domain.member.exception.code.MemberErrorCode;
import spring.umc.domain.member.repository.InquiryImageRepository;
import spring.umc.domain.member.repository.InquiryRepository;
import spring.umc.domain.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InquiryServiceImpl implements InquiryService {

    private final InquiryRepository inquiryRepository;
    private final MemberRepository memberRepository;
    private final InquiryImageRepository inquiryImageRepository;

    @Override
    @Transactional
    public Inquiry createInquiry(Long memberId, String title, String content, String imageUrl) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        InquiryImage inquiryImage = InquiryImage.builder()
                .inquiryImageUrl(imageUrl)
                .sequence(1)
                .build();

        InquiryImage savedInquiryImage = inquiryImageRepository.save(inquiryImage);

        Inquiry inquiry = Inquiry.builder()
                .member(member)
                .title(title)
                .content(content)
                .inquiryImage(savedInquiryImage)
                .build();

        return inquiryRepository.save(inquiry);
    }

    @Override
    public Page<Inquiry> getMyInquiries(Long memberId, String titleKeyword, Pageable pageable) {
        memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        return inquiryRepository.searchInquiries(memberId, titleKeyword, pageable);
    }
}