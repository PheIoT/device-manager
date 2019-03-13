package com.pheiot.phecloud.pd.api;

import com.pheiot.phecloud.pd.openapi.dto.ProductVO;
import com.pheiot.phecloud.pd.openapi.v1.ProductFacade;
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

/**
 * @author:qinyimei @Data:2018/11/7 9:23
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class ProductFacadeTest {

    @Autowired
    private ProductFacade productFacade;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(productFacade).build();
    }

    private static String PHE_APPLICATION_TOKEN = "phe_application_token";

    @Test
    public void saveOrUpdate() throws Exception {
        ProductVO request = new ProductVO();
        request.setProduct_name("apiTest");
        request.setProduct_type("normal");
        request.setUser_key("user-key-1");


        String jsonString = com.alibaba.fastjson.JSONObject.toJSONString(request);
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.put("/app/v1/product")
                        .contentType(MediaType.APPLICATION_JSON)
//                        .header(PHE_APPLICATION_TOKEN,"abc")
//                        .accept(MediaType.valueOf(PHE_APPLICATION_TOKEN))
                        .content(jsonString)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        System.out.println("输出 " + mvcResult.getResponse().getContentAsString());

    }
}
