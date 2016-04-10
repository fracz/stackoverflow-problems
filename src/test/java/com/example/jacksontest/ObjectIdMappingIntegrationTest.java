package com.example.jacksontest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigWebContextLoader.class, classes = WebappConfig.class)
@WebAppConfiguration
public class ObjectIdMappingIntegrationTest {
    @Autowired
    private WebApplicationContext context;

    @Test
    public void testMappingObjectId() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.notNested", equalTo(MyRestController.JERRY_ID)))
                .andExpect(jsonPath("$.parameters", notNullValue()))
                .andExpect(jsonPath("$.parameters.tom", equalTo("Cat")))
                .andExpect(jsonPath("$.parameters.jerry", equalTo(MyRestController.JERRY_ID)));
    }
}
