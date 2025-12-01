package spring.umc.global.config;

import spring.umc.global.config.web.argumentresolver.PageValidationArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        // 만든 Argument Resolver를 스프링 목록에 추가!
        resolvers.add(new PageValidationArgumentResolver());
    }
}