package com.rubix.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

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
import com.rubix.dto.CubeDTO;
import com.rubix.repository.CubeRepositoryTest;

@ActiveProfiles({"test", "unit" })
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = UnitTestConfig.class)
public class CubeControllerTest extends CubeRepositoryTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Override
    @Before
    public void setup() {
        super.setup();
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    // @formatter:off
    @Test
    public void testCheckExists() throws Exception {
        final MvcResult mvcResult =
                this.mockMvc
                    .perform(
                        post("/checkExists")
                            .contentType(MediaType.APPLICATION_JSON_UTF8)
                            .content(getJsonContent(getCubeMock()))
                    )
                    .andExpect(request().asyncStarted())
                    .andReturn();

        this.mockMvc
            .perform(asyncDispatch(mvcResult))
            .andExpect(status().isOk())
            // todo add feed db entries and this will be solved if sending a valid value (i.e. not null)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(content().string(""));
    }
    // @formatter:on

    private String getJsonContent(final CubeDTO cube) throws JsonProcessingException {
        final ObjectMapper om = new ObjectMapper();
        return om.writeValueAsString(cube);
    }

    private CubeDTO getCubeMock() {
        final CubeDTO cube = new CubeDTO();
        cube.setFaces(Arrays.asList("cece", "fabb", "afaf", "ecdd", "dbfc", "bdea"));
        cube.setSize(4);
        return cube;
    }
}
