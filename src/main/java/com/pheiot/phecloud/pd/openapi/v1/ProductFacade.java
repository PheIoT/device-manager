/*
 * Copyright (c) 2018. For PheIot Group.
 */

package com.pheiot.phecloud.pd.openapi.v1;

import com.google.common.collect.Lists;
import com.pheiot.phecloud.pd.dto.ProductConditionDto;
import com.pheiot.phecloud.pd.dto.ProductDto;
import com.pheiot.phecloud.pd.openapi.ResponseEntity;
import com.pheiot.phecloud.pd.openapi.ResponsePageEntity;
import com.pheiot.phecloud.pd.openapi.exception.BusinessException;
import com.pheiot.phecloud.pd.openapi.v1.vo.ProductVO;
import com.pheiot.phecloud.pd.service.ProductService;
import com.pheiot.phecloud.pd.utils.ApplicationException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author Peter Li
 */
@RestController
@RequestMapping("/api/v1/product")
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
    public ResponseEntity<ProductDto> changeEnabledTo(@PathVariable("key") String productKey, @RequestBody Map body) {
        ProductVO responseVo = new ProductVO();

        try {
            if (body == null || body.get("is_enabled") == null || StringUtils.isBlank(body.get("is_enabled").toString())) {
                throw new BusinessException("Parameter error.");
            }

            ProductDto dto = productService.changeEnabledTo(productKey, Boolean.valueOf(body.get("is_enabled").toString()));
            ProductVO.dto2Vo(dto, responseVo);
        } catch (BusinessException ex) {
            log.error("Updating product status error.{}", ex.getMessage());
            return ResponseEntity.ofFailed().data("Updating product status error.");
        } catch (ApplicationException ex) {
            log.error("Updating product status error.{}", ex.getMessage());
            return ResponseEntity.ofFailed().data("Updating product status error.");
        }

        return ResponseEntity.ofSuccess().status(HttpStatus.OK).data(responseVo);
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

    @GetMapping
    public ResponsePageEntity findAllProduct(@RequestParam(value="limit", required=false) Integer limit,
                                                     @RequestParam(value="offset", required=false) Integer offset,
                                                     @RequestParam(value="show_disabled", required=false) Boolean showDisabled,
                                                     @RequestHeader("phe-application-user-token") String userToken) {

        ProductConditionDto pageableDto = new ProductConditionDto();
        pageableDto.setLimit(limit);
        pageableDto.setOffset(offset);
        pageableDto.setShowDisabled(showDisabled);

        List<ProductVO> responseVoList = Lists.newArrayList();

        try {
            //TODO: userToken的提取和校验，传入service的应为uid

            List<ProductDto> dtos = productService.findByUidPageable(userToken, pageableDto);
            for (ProductDto dto : dtos) {
                responseVoList.add(ProductVO.dto2Vo(dto));
            }

        } catch (ApplicationException ex) {
            log.error("Find product error.{}", ex.getMessage());
            return ResponsePageEntity.ofFailed().data("Find product status error.");
        }

        return ResponsePageEntity.ofSuccess().status(HttpStatus.OK).data(responseVoList).total(responseVoList.size());
    }

}
