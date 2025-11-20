package spring.umc.domain.member.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import spring.umc.domain.member.converter.MemberConverter;
import spring.umc.domain.member.dto.req.MemberRequestDTO;
import spring.umc.domain.member.dto.res.MemberResponseDTO;
import spring.umc.domain.member.dto.res.MemberResponseDTO.MemberDTO;
import spring.umc.domain.member.entity.Member;
import spring.umc.domain.member.enums.Gender;
import spring.umc.domain.member.enums.Status;
import spring.umc.domain.member.exception.code.MemberSuccessCode;
import spring.umc.domain.member.service.MemberService;
import spring.umc.global.apiPayload.ApiResponse;
import spring.umc.global.apiPayload.code.GeneralSuccessCode;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/{memberId}")
    public ApiResponse<MemberDTO> getMemberById(@PathVariable Long memberId) {
        Member member = memberService.findMemberById(memberId);
        GeneralSuccessCode code = GeneralSuccessCode.OK;
        return ApiResponse.onSuccess(code, MemberConverter.toMemberDTO(member));
    }

    @GetMapping("/search")
    public ApiResponse<MemberResponseDTO.MemberPageListDTO> searchMembers(
            @RequestParam(required = false) String nameKeyword,
            @RequestParam(required = false) Gender gender,
            @RequestParam(required = false) Status status,
            @PageableDefault(size = 10) Pageable pageable) {
        Page<Member> memberPage = memberService.searchMembers(nameKeyword, gender, status, pageable);
        GeneralSuccessCode code = GeneralSuccessCode.OK;
        return ApiResponse.onSuccess(code, MemberConverter.toMemberPageListDTO(memberPage));
    }

    /**
     * 선호 음식 변경 (PreferFood 로직)
     */
    @PutMapping("/{memberId}/prefer-foods")
    public ApiResponse<String> updatePreferFoods(
            @PathVariable Long memberId,
            @RequestBody MemberRequestDTO.UpdatePreferFoodsDTO request) {
        memberService.updatePreferFoods(memberId, request.getCategoryIds());
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, "선호 음식이 변경되었습니다.");
    }

    /**
     * 회원가입
     */
    @PostMapping("/sign-up")
    public ApiResponse<MemberResponseDTO.JoinDTO> signUp(
            @RequestBody @Valid MemberRequestDTO.JoinDTO dto
    ){
        return ApiResponse.onSuccess(MemberSuccessCode.FOUND, memberService.signup(dto));
    }
}