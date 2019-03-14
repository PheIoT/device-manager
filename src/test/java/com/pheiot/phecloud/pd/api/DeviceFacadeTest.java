package com.pheiot.phecloud.pd.api;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.pheiot.bamboo.common.utils.mapper.JsonMapper;
import com.pheiot.phecloud.pd.openapi.v1.DeviceFacade;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class DeviceFacadeTest {

    @Autowired
    private DeviceFacade facade;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(facade).build();
    }

    private static String PHE_APPLICATION_TOKEN = "phe-application-token";

    @Test
    public void unbinding() throws Exception {
        Map request = Maps.newHashMap();
        request.put("product_key", "a");
        List keys = Lists.newArrayList();
        keys.add("a");
        keys.add("b");
        request.put("devices", keys);


        String jsonString = JsonMapper.defaultMapper().toJson(request);
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.post("/app/v1/device/unbinding")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(PHE_APPLICATION_TOKEN, "abc")
                        .content(jsonString)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

    }

    @Test
    public void changeEnabledTo() throws Exception {
        String kay = "7HQoQxINSr";
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.patch("/app/v1/product/{kay}/enabled", kay)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(PHE_APPLICATION_TOKEN, "abc")
                        .param("is_enabled", "false")
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

    }

    @Test
    public void findProductByKey() throws Exception {
        String kay = "7HQoQxINSr";

        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.get("/app/v1/product/{key}", kay)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(PHE_APPLICATION_TOKEN, "abc")
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

    }
}
