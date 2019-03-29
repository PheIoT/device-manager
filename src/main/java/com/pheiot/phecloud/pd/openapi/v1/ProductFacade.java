/*
 * Copyright (c) 2018. For PheIot Group.
 */

package com.pheiot.phecloud.pd.openapi.v1;

import com.google.common.collect.Maps;
import com.pheiot.phecloud.pd.dto.ProductDto;
import com.pheiot.phecloud.pd.openapi.ResponseEntity;
import com.pheiot.phecloud.pd.openapi.v1.vo.ProductVO;
import com.pheiot.phecloud.pd.service.ProductService;
import com.pheiot.phecloud.pd.utils.ApplicationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author Peter Li
 */
@RestController
@RequestMapping("/app/v1/product")
public class ProductFacade {

    private static Logger log = LoggerFactory.getLogger(ProductFacade.class);

    @Resource
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductVO productVO) {
        ProductDto dto = ProductVO.vo2Dto(productVO);

        ProductDto responseDto;
        ProductVO responseVo = new ProductVO();

        try {
            responseDto = productService.save(dto);
            ProductVO.dto2Vo(responseDto, responseVo);
        } catch (ApplicationException ex) {
            log.error("Save product error.{}", ex.getMessage());
            return ResponseEntity.ofFailed().data("Save product error.");
        }

        return ResponseEntity.ofSuccess().status(HttpStatus.OK).data(responseVo);
    }

    @PatchMapping("/{key}/enabled")
    public ResponseEntity<ProductDto> changeEnabledTo(@PathVariable("key") String productKey, @RequestParam("is_enabled") boolean isEnabled) {
        try {
            productService.changeEnabledTo(productKey, isEnabled);
        } catch (ApplicationException ex) {
            log.error("Updating product status error.{}", ex.getMessage());
            return ResponseEntity.ofFailed().data("Updating product status error.");
        }

        Map response = Maps.newHashMap();
        response.put("product_key", productKey);
        response.put("is_enabled", isEnabled);

        return ResponseEntity.ofSuccess().status(HttpStatus.OK).data(response);
    }

    @GetMapping("/{key}")
    public ResponseEntity<ProductDto> findProductByKey(@PathVariable("key") String productKey) {
        ProductVO responseVo = new ProductVO();

        try {
            ProductDto dto = productService.findProductByKey(productKey);
            ProductVO.dto2Vo(dto, responseVo);
        } catch (ApplicationException ex) {
            log.error("Find product error.{}", ex.getMessage());
            return ResponseEntity.ofFailed().data("Find product status error.");
        }


        return ResponseEntity.ofSuccess().status(HttpStatus.OK).data(responseVo);
    }

}
