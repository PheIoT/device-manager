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
        product.setName("ABCDE");
        product.setType(RandomStringUtils.randomAlphanumeric(5));
        productDao.save(product);

        Product p = productDao.findByName("ABCDE");
        assertThat(p.getName()).isEqualTo("ABCDE");
    }

    @Test
    @Transactional
    public void delete() {
        List<Long> ids = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Product product = new Product();
            product.setName(RandomStringUtils.randomAlphanumeric(5));
            product.setType(RandomStringUtils.randomAlphanumeric(5));
            productDao.save(product);
            ids.add(product.getId());
        }
        productDao.deleteByIds(ids);//批量删除

        assertThat(productDao.findAll()).hasSize(0);
    }

    /**
     * 查询所有的产品名称
     */
    @Test
    @Transactional
    public void listAllProductNames() {
        List<String> names = productDao.findProductNames("userKey");
        assertThat(names.size()).isNotNull();
    }

    /**
     * 根据产品名称进行模糊查询
     */
    @Test
    @Transactional
    public void findProductByNameLike() {
        for (int i = 0; i < 3; i++) {
            Product product = new Product();
            product.setKay(RandomStringUtils.randomAlphanumeric(32));
            product.setName("s" + RandomStringUtils.randomAlphanumeric(5));
            product.setType(RandomStringUtils.randomAlphanumeric(5));
            product.setUserKey("userkey");
            productDao.save(product);
        }
        List<Product> lists = productDao.findByNameLike("s", "userkey");
        if (lists.size() != 0) {
            for (Product p : lists) {
                System.out.println(p.getId() + " | " + p.getName());
            }
        }
    }

    /**
     * 根据产品ID查询
     */
    @Test
    public void findById() {
        Optional<Product> currentProduct = productDao.findById(112L);
        assertThat(currentProduct.get()).isNotNull();
    }

    /**
     * 根据产品名称查询
     */
    @Test
    public void findByName() {
        Optional<Product> currentProduct = productDao.findById(112L);
        Product targetProduct = productDao.findByName(currentProduct.get().getName());
        Assert.assertEquals(targetProduct, currentProduct);
    }
}
