package spring.umc.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GeneralSuccessCode implements  BaseSuccessCode {

    OK(HttpStatus.OK,
            "SUCCESS200_1",
            "요청이 성공적으로 처리되었습니다."),

    CREATED(HttpStatus.CREATED,
            "SUCCESS201_1",
            "리소스가 성공적으로 생성되었습니다."),

    ACCEPTED(HttpStatus.ACCEPTED,
            "SUCCESS202_1",
            "요청이 접수되었으며, 처리가 진행 중입니다."),

    NO_CONTENT(HttpStatus.NO_CONTENT,
            "SUCCESS204_1",
            "요청이 성공적으로 처리되었으나 반환할 데이터가 없습니다."),

    REVIEW_LIST_OK(HttpStatus.OK,
            "REVIEW200_1",
            "내가 작성한 리뷰 목록을 성공적으로 조회했습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
