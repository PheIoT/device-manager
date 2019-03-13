package com.pheiot.phecloud.pd.openapi.v1;

import com.pheiot.phecloud.pd.dto.ProductDto;
import com.pheiot.phecloud.pd.openapi.ResponseEntity;
import com.pheiot.phecloud.pd.openapi.dto.ProductVO;
import com.pheiot.phecloud.pd.service.ProductService;
import com.pheiot.phecloud.pd.utils.ApplicationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author qinyimei @Data 2018/10/24 13:36
 */
@RestController
@RequestMapping("/app/v1/product")
public class ProductFacade {

    private static Logger log = LoggerFactory.getLogger(ProductFacade.class);

    @Resource
    private ProductService productService;

    @PutMapping
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

        return ResponseEntity.ofSuccess().data(responseVo);
    }
}
