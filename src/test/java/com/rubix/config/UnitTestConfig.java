package com.rubix.config;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.mockito.stubbing.Answer;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.rubix.constant.TestCubeConstants;
import com.rubix.entity.CubeEntity;
import com.rubix.factory.TestCubeFactory;
import com.rubix.repository.CubeCustomRepository;
import com.rubix.repository.impl.CubeRepositoryImpl;

@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.rubix" })
@Configuration
public class UnitTestConfig {

    @Bean
    @Primary
    public CubeCustomRepository cubeMockRepository() {
        final CubeRepositoryImpl repositoryMock = mock(CubeRepositoryImpl.class);
        // TODO refactor this put it somewhere where it makes more sense
        // regular find
        when(repositoryMock.findByKey(TestCubeConstants.EXISTING[0], TestCubeConstants.COLLECTION))
                .thenReturn(TestCubeFactory.getCubeMock(TestCubeConstants.EXISTING[0]));
        // longer-taking finds
        when(repositoryMock.findByKey(TestCubeConstants.EXISTING[1], TestCubeConstants.COLLECTION))
                .thenAnswer(getAnswer(TestCubeConstants.EXISTING[1]));
        when(repositoryMock.findByKey(TestCubeConstants.EXISTING[2], TestCubeConstants.COLLECTION))
                .thenAnswer(getAnswer(TestCubeConstants.EXISTING[2]));
        return repositoryMock;
    }

    /**
     * Simulate possibility of one repository thread taking much longer than the others
     * 
     * @return
     */
    private Answer<CubeEntity> getAnswer(String key) {
        final Answer<CubeEntity> alternateExecution = (inv) -> {
            //
            Thread.sleep(3000);
            return TestCubeFactory.getCubeMock(key);
        };
        // still new to this stuff :)
        return alternateExecution;
    }
/*
    // @formatter:off
    public CubeCustomRepository cubeMockRepositoryOld() {
        CubeRepositoryImpl repositoryMock = mock(CubeRepositoryImpl.class);
        final CubeEntity ce = new CubeEntity();
        ce.setKey("Muie");
        // todo refactor this put it somewhere where it makes more sense
        when(repositoryMock.findByKey(TestCubeConstants.EXISTING[0], TestCubeConstants.COLLECTION)).thenReturn(ce);
        when(repositoryMock.findByKey(TestCubeConstants.EXISTING[1], TestCubeConstants.COLLECTION))
                .thenAnswer(new Answer<CubeEntity>() {

                    @Override
                    public CubeEntity answer(InvocationOnMock invocation) throws Throwable {
                        // simulate possibility of one repository thread taking much longer than the others
                        Thread.sleep(3000);
                        return ce;
                    }
                });
        return repositoryMock;
    }
*/
}
