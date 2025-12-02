package spring.umc.domain.store.exception.code;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import spring.umc.global.apiPayload.code.BaseSuccessCode;

@Getter
public enum StoreSuccessCode implements BaseSuccessCode {

    // --- 성공 상수 정의 ---
    // HttpStatus.CREATED (201): 새로운 리소스가 성공적으로 생성되었을 때
    REVIEW_CREATE_SUCCESS(HttpStatus.CREATED, "리뷰가 성공적으로 등록되었습니다."),

    // HttpStatus.OK (200): 리소스 조회 또는 단순 성공
    REVIEW_READ_SUCCESS(HttpStatus.OK, "리뷰를 성공적으로 조회했습니다. (일반 조회)"),

    // HttpStatus.OK (200): 리소스를 성공적으로 찾았을 때 (특정 ID로 조회 등)
    REVIEW_FOUND_SUCCESS(HttpStatus.OK, "요청하신 리뷰를 성공적으로 찾았습니다."),

    REVIEW_UPDATE_SUCCESS(HttpStatus.OK, "리뷰가 성공적으로 수정되었습니다."),

    // HttpStatus.NO_CONTENT (204): 성공적으로 처리했지만 응답 바디가 없을 때 (주로 삭제)
    REVIEW_DELETE_SUCCESS(HttpStatus.NO_CONTENT, "리뷰가 성공적으로 삭제되었습니다."),

    ;

    private final HttpStatus httpStatus;
    private final String message;

    // 생성자
    StoreSuccessCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    @Override
    public HttpStatus getStatus() {
        return null;
    }

    @Override
    public String getCode() {
        return "";
    }
}