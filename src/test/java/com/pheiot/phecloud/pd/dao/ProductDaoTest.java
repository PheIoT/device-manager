package com.pheiot.phecloud.pd.dao;

import com.pheiot.phecloud.pd.entity.Product;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@DirtiesContext
public class ProductDaoTest {

    @Autowired
    private ProductDao productDao;

    @Test
    @Transactional
    public void save() {
        Product product = new Product();
        product.setDisplayName("ABCDE");
        product.setNodeType("device");
        productDao.save(product);

        Product p = productDao.findByDisplayName("ABCDE");
        assertThat(p.getDisplayName()).isEqualTo("ABCDE");
    }

    @Test
    @Transactional
    public void delete() {
        List<Long> ids = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Product product = new Product();
            product.setDisplayName(RandomStringUtils.randomAlphanumeric(5));
            product.setNodeType("device");
            productDao.save(product);
            ids.add(product.getId());
        }
        productDao.deleteByIds(ids);//批量删除

        assertThat(productDao.findAll()).hasSize(0);
    }

    /**
     * 根据产品ID查询
     */
    @Test
    public void findById() {
        Optional<Product> currentProduct = productDao.findById(1L);
        assertThat(currentProduct.get()).isNotNull();
    }

    /**
     * 根据产品名称查询
     */
    @Test
    public void findByName() {
        Optional<Product> currentProduct = productDao.findById(1L);
        Product targetProduct = productDao.findByDisplayName(currentProduct.get().getDisplayName());
        Assert.assertEquals(targetProduct, currentProduct);
    }
}
