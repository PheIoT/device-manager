/*
 * Copyright (c) 2018. For PheIot Group.
 */

/*
 * Copyright (c) 2018. For PheIot Group.
 */

package com.pheiot.phecloud.pd.dao;

import com.pheiot.phecloud.pd.entity.ProductProperty;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@DirtiesContext
public class ProductPropertyDaoTest {

    @Autowired
    private ProductPropertyDao dao;

    private String productKey = "testkey-12345678901234567890abcd";

    /**
     * 根据code查询
     */
    @Test
    public void existsByPkeyAndCode() {
        String code = "LED-ONOFF";
        boolean result = dao.existsByPkeyAndCode(productKey, code);
        assertThat(result).isEqualTo(true);
    }

    /**
     * 根据code查询
     */
    @Test
    public void findByProductAndCode() {
        String code = "LED-ONOFF";
        ProductProperty entity = dao.findByProductAndCode(productKey, code);
        assertThat(entity.getCode()).isEqualTo(code);
    }
}
