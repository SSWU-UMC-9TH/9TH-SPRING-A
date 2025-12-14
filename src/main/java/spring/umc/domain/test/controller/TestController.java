package spring.umc.domain.test.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spring.umc.domain.test.converter.TestConverter;
import spring.umc.domain.test.dto.TestResDTO;
import spring.umc.domain.test.exeption.TestException;
import spring.umc.domain.test.service.query.TestQueryService;
import spring.umc.global.apiPayload.ApiResponse;
import spring.umc.global.apiPayload.code.GeneralErrorCode;
import spring.umc.global.apiPayload.code.GeneralSuccessCode;

@RestController
@RequiredArgsConstructor
@RequestMapping("/temp")
public class TestController {

    private final TestQueryService testQueryService;

    @GetMapping("/test")
    public ApiResponse<TestResDTO.Testing> test() throws Exception {
        // 응답 코드 정의
        GeneralSuccessCode code = GeneralSuccessCode._OK;
        throw new TestException(GeneralErrorCode.INTERNAL_SERVER_ERROR);
//        return ApiResponse.onSuccess(
//                code,
//                TestConverter.toTestingDTO("This is Test!")
//        );
    }
    @GetMapping("/exception")
    public ApiResponse<TestResDTO.Exception> exception(
            @RequestParam Long flag
    ) {

        testQueryService.checkFlag(flag);

        // 응답 코드 정의
        GeneralSuccessCode code = GeneralSuccessCode._OK;
        return ApiResponse.onSuccess(code, TestConverter.toExceptionDTO("This is Test!"));
    }
}
