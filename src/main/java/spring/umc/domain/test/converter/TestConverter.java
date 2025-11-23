package spring.umc.domain.test.converter;

import spring.umc.domain.test.dto.res.TestResDTO;

public class TestConverter { // 도메인 객체를 응답 DTO로 변환


    // 객체 -> DTO (테스트용 DTO 변환 메서드)
    public static TestResDTO.Testing toTestingDTO(
            String testing
    ) {
        return TestResDTO.Testing.builder()
                .testString(testing)
                .build();
    }

    // 객체 -> DTO (예외 테스트용 DTO 변환 메서드)
    public static TestResDTO.Exception toExceptionDTO(
            String testing
    ){
        return TestResDTO.Exception.builder()
                .testString(testing)
                .build();
    }

}