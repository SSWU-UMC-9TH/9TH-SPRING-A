package spring.umc.domain.member.converter;

import org.springframework.data.domain.Page;
import spring.umc.domain.member.dto.res.InquiryResponseDTO;
import spring.umc.domain.member.entity.Inquiry;

import java.util.List;
import java.util.stream.Collectors;

public class InquiryConverter {

    /**
     * 문의 생성 결과 DTO
     */
    public static InquiryResponseDTO.CreateInquiryResultDTO toCreateInquiryResultDTO(Inquiry inquiry) {
        return InquiryResponseDTO.CreateInquiryResultDTO.builder()
                .inquiryId(inquiry.getInquiryId())
                .memberName(inquiry.getMember().getName())
                .title(inquiry.getTitle())
                .createdAt(inquiry.getCreatedAt())
                .build();
    }

    /**
     * Entity -> DTO (단일 조회용)
     */
    public static InquiryResponseDTO.InquiryDTO toInquiryDTO(Inquiry inquiry) {
        return InquiryResponseDTO.InquiryDTO.builder()
                .inquiryId(inquiry.getInquiryId())
                .title(inquiry.getTitle())
                .content(inquiry.getContent())
                .inquiryImageUrl(inquiry.getInquiryImage().getInquiryImageUrl())
                .createdAt(inquiry.getCreatedAt())
                .build();
    }

    /**
     * Page<Entity> -> DTO (목록)
     */
    public static InquiryResponseDTO.InquiryPageListDTO toInquiryPageListDTO(Page<Inquiry> inquiryPage) {
        List<InquiryResponseDTO.InquiryDTO> inquiryList = inquiryPage.getContent().stream()
                .map(InquiryConverter::toInquiryDTO)
                .collect(Collectors.toList());

        return InquiryResponseDTO.InquiryPageListDTO.builder()
                .inquiryList(inquiryList)
                .listSize(inquiryList.size())
                .totalPage(inquiryPage.getTotalPages())
                .totalElements(inquiryPage.getTotalElements())
                .isFirst(inquiryPage.isFirst())
                .isLast(inquiryPage.isLast())
                .build();
    }
}