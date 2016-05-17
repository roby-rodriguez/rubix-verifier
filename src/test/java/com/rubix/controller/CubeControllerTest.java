package com.rubix.controller;

import static org.junit.Assert.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rubix.config.UnitTestConfig;
import com.rubix.constant.CubeConstants;
import com.rubix.constant.TestCubeConstants;
import com.rubix.dto.CubeDTO;
import com.rubix.entity.CubeEntity;
import com.rubix.factory.TestCubeFactory;
import com.rubix.repository.CubeRepositoryTest;
import com.rubix.util.CubeCheckerServiceManager;

@ActiveProfiles({"test", "unit" })
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = UnitTestConfig.class)
public class CubeControllerTest extends CubeRepositoryTest {

    private MockMvc mockMvc;

    /** Test cube for request */
    private CubeDTO testCubeReq;

    /** Test cube for response */
    private CubeEntity testCubeResp;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private CubeCheckerServiceManager cubeCheckerServiceManager;

    @Override
    @Before
    public void setup() {
        super.setup();
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        // autowiring won't work since this bean is prototype-scoped, we need to extract it from the web ctx
        // this.cubeCheckerServiceManager =
        // this.webApplicationContext.getBean("cubeCheckerServiceManager", CubeCheckerServiceManager.class);
        System.out.println("CHECKER=" + this.cubeCheckerServiceManager);
        this.testCubeReq = TestCubeFactory.getCubeMock(TestCubeConstants.CUBE_FACES, CubeConstants.SIZE4);
        this.testCubeResp = TestCubeFactory.getCubeMock(TestCubeConstants.EXISTING[0]);
    }

    // @formatter:off
    @Test
    public void testCheckExists() throws Exception {
        final MvcResult result =
                this.mockMvc
                    .perform(
                        post("/checkExists")
                            .contentType(MediaType.APPLICATION_JSON_UTF8)
                            .content(getJsonContent(this.testCubeReq))
                    )
                    .andExpect(request().asyncStarted())
                    .andReturn();

        this.mockMvc
            .perform(asyncDispatch(result))
            .andExpect((mvcResult) -> {
                System.out.println("CHECKER=" + this.cubeCheckerServiceManager);
                assertFalse("Some finder threads should still be running when the result is returned", 
                        cubeCheckerServiceManager.checkFinished());
            })
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(content().string(getJsonContent(this.testCubeResp)));
    }
    // @formatter:on

    private <C> String getJsonContent(final C cube) throws JsonProcessingException {
        final ObjectMapper om = new ObjectMapper();
        return om.writeValueAsString(cube);
    }
}
