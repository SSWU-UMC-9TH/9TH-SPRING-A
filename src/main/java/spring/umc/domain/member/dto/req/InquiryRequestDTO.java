package spring.umc.domain.member.dto.req;

import lombok.Getter;

public class InquiryRequestDTO {

    @Getter
    public static class CreateInquiryDTO {
        private String title;
        private String content;
        private String imageUrl;
    }
}