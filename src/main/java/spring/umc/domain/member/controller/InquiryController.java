package spring.umc.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import spring.umc.domain.member.converter.InquiryConverter;
import spring.umc.domain.member.dto.req.InquiryRequestDTO;
import spring.umc.domain.member.dto.res.InquiryResponseDTO;
import spring.umc.domain.member.dto.res.InquiryResponseDTO.CreateInquiryResultDTO;
import spring.umc.domain.member.entity.Inquiry;
import spring.umc.domain.member.service.InquiryService;
import spring.umc.global.apiPayload.ApiResponse;
import spring.umc.global.apiPayload.code.GeneralSuccessCode;

@RestController
@RequestMapping("/inquiries")
@RequiredArgsConstructor
public class InquiryController {

    private final InquiryService inquiryService;

    /**
     * 문의 생성
     */
    @PostMapping
    public ApiResponse<CreateInquiryResultDTO> createInquiry(
            @RequestParam Long memberId,
            @RequestBody InquiryRequestDTO.CreateInquiryDTO request) {
        Inquiry inquiry = inquiryService.createInquiry(
                memberId,
                request.getTitle(),
                request.getContent(),
                request.getImageUrl()
        );
        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, InquiryConverter.toCreateInquiryResultDTO(inquiry));
    }

    /**
     * 내 문의 목록 조회 (QueryDSL)
     */
    @GetMapping("/my")
    public ApiResponse<InquiryResponseDTO.InquiryPageListDTO> getMyInquiries(
            @RequestParam Long memberId,
            @RequestParam(required = false) String titleKeyword,
            @PageableDefault(size = 10) Pageable pageable) {
        Page<Inquiry> inquiryPage = inquiryService.getMyInquiries(memberId, titleKeyword, pageable);
        GeneralSuccessCode code = GeneralSuccessCode.OK;
        return ApiResponse.onSuccess(code, InquiryConverter.toInquiryPageListDTO(inquiryPage));
    }
}