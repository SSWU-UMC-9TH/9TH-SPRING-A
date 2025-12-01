package spring.umc.domain.test.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.umc.domain.test.service.query.TestQueryService;

@Service
@RequiredArgsConstructor
public class TestCommandServiceImpl implements TestCommandService {
    @Override
    public void checkFlag(Long flag){}
}
