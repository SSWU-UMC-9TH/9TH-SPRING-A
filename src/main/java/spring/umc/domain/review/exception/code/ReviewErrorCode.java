package spring.umc.domain.review.exception.code;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import spring.umc.global.apiPayload.code.BaseErrorCode;

@Getter
@AllArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {


    REVIEW_SEARCH_TYPE_INVALID(HttpStatus.BAD_REQUEST, "REVIEW4001", "유효하지 않은 검색 타입입니다. ('location', 'star', 'both'만 가능)"),
    REVIEW_STAR_QUERY_NOT_NUMBER(HttpStatus.BAD_REQUEST, "REVIEW4002", "별점은 숫자 형식이어야 합니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND, "REVIEW404_1", "해당 리뷰를 찾을 수 없습니다.");


    private final HttpStatus status;
    private final String code;
    private final String message;
}