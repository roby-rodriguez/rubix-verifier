package com.rubix.config;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.rubix.entity.CubeEntity;
import com.rubix.repository.CubeCustomRepository;
import com.rubix.repository.CubeRepositoryTest;
import com.rubix.repository.impl.CubeRepositoryImpl;

@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.rubix" })
@Configuration
public class UnitTestConfig {

    @Bean
    @Primary
    public CubeCustomRepository cubeMockRepository() {
        CubeRepositoryImpl repositoryMock = mock(CubeRepositoryImpl.class);
        final CubeEntity ce = new CubeEntity();
        ce.setKey("Muie");
        // todo refactor this put it somewhere where it makes more sense
        when(repositoryMock.findByKey(CubeRepositoryTest.EXISTING[0], CubeRepositoryTest.COLLECTION)).thenReturn(ce);
        when(repositoryMock.findByKey(CubeRepositoryTest.EXISTING[1], CubeRepositoryTest.COLLECTION))
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
}
