/*
 * Copyright (c) 2018. For PheIot Group.
 */

package com.pheiot.phecloud.pd.service.impl;

import com.pheiot.bamboo.common.utils.mapper.BeanMapper;
import com.pheiot.phecloud.pd.dao.ProductDao;
import com.pheiot.phecloud.pd.dto.ProductDto;
import com.pheiot.phecloud.pd.entity.Product;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductServiceTest {

	private static Logger logger = LoggerFactory.getLogger(ProductServiceTest.class);
	protected ProductDao mockProductDao;
	protected ProductServiceImpl service;

	@Before
	public void setup() {
		service = new ProductServiceImpl();
		mockProductDao = Mockito.mock(ProductDao.class);
		service.productDao = mockProductDao;
	}

	public void tearDown() {
	}


	@Test
	public void save() {
		Product entity = new Product();
		entity.setPkey("key1");
		entity.setDisplayName("testProduct");

		ProductDto dto = BeanMapper.map(entity, ProductDto.class);
		service.save(dto);

		Mockito.verify(mockProductDao).save(Mockito.any(Product.class));
	}

	@Test
	public void findById() {
		Product entity = new Product();
		entity.setPkey("key1");
		entity.setDisplayName("testProduct");

		Mockito.when(mockProductDao.findById(1L)).thenReturn(Optional.of(entity));

		ProductDto dto = service.findProductById(1L);

		Mockito.verify(mockProductDao).findById(1L);

		assertThat(dto.getPkey()).isEqualTo("key1");
	}

}
