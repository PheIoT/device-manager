/*
 * Copyright (c) 2018. For PheIot Group.
 */

/*
 * Copyright (c) 2018. For PheIot Group.
 */

package com.pheiot.phecloud.pd.openapi.v1;

import com.pheiot.phecloud.pd.dto.ProductPropertyFullDto;
import com.pheiot.phecloud.pd.openapi.ResponseEntity;
import com.pheiot.phecloud.pd.openapi.v1.vo.ProductPropertyVO;
import com.pheiot.phecloud.pd.service.ProductPropertyService;
import com.pheiot.phecloud.pd.utils.ApplicationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Peter Li
 */
@RestController
@RequestMapping("/api/v1/property")
public class ProductPropertyFacade {

    private static Logger log = LoggerFactory.getLogger(ProductPropertyFacade.class);

    @Resource
    private ProductPropertyService productPropertyService;

    @GetMapping("/{pkey}")
    public ResponseEntity<ProductPropertyVO> findProductPropertyByPKey(@PathVariable("pkey") String productKey) {

        ProductPropertyVO responseVO = new ProductPropertyVO();

        try {
            ProductPropertyFullDto dto = productPropertyService.findFullByProductKey(productKey);
            ProductPropertyVO.dto2Vo(dto, responseVO);

        } catch (ApplicationException ex) {
            return ResponseEntity.ofFailed().data("Find property error.");
        }

        return ResponseEntity.ofSuccess().status(HttpStatus.OK).data(responseVO);
    }
}
