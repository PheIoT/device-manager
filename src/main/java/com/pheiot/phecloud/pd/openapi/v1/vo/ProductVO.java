package com.pheiot.phecloud.pd.openapi.v1.vo;

import com.pheiot.bamboo.common.dto.AbstractValueObject;
import com.pheiot.phecloud.pd.dto.ProductDto;
import lombok.Data;
import org.apache.commons.lang.StringUtils;

import java.sql.Timestamp;

@Data
public class ProductVO extends AbstractValueObject {
    //产品key
    private String product_key;
    //产品秘钥
    private String product_secret;
    //产品名称
    private String product_name;
    //类型 Normal / Gateway
    private String product_type;
    //用户key
    private String user_key;
    //备注
    private String remark;
    //创建时间
    private String created_at;
    //是否启用
    private Boolean is_enabled;

    public static void dto2Vo(ProductDto dto, ProductVO vo) {
        convert2Vo(dto, vo);
    }

    public static ProductVO dto2Vo(ProductDto dto) {
        ProductVO vo = new ProductVO();
        convert2Vo(dto, vo);
        return vo;
    }

    public static void vo2Dto(ProductVO vo, ProductDto dto) {
        convert2Dto(vo, dto);
    }

    public static ProductDto vo2Dto(ProductVO vo) {
        ProductDto dto = new ProductDto();
        convert2Dto(vo, dto);
        return dto;
    }

    private static void convert2Vo(ProductDto dto, ProductVO vo) {
        vo.setProduct_key(dto.getKay());
        vo.setProduct_name(dto.getName());
        vo.setProduct_secret(dto.getSecret());
        vo.setProduct_type(dto.getType());
        vo.setUser_key(dto.getUserKey());
        vo.setRemark(dto.getRemark());
        vo.setCreated_at(dto.getCreateAt().toString());
        vo.setIs_enabled(dto.isEnabled());
    }

    private static void convert2Dto(ProductVO vo, ProductDto dto) {
        dto.setKay(vo.getProduct_key());
        dto.setName(vo.getProduct_name());
        dto.setSecret(vo.getProduct_secret());
        dto.setType(vo.getProduct_type());
        dto.setUserKey(vo.getUser_key());
        dto.setRemark(vo.getRemark());


        if (StringUtils.isNotBlank(vo.getCreated_at())) {
            Timestamp ts;
            String tsStr = vo.getCreated_at();
            try {
                ts = Timestamp.valueOf(tsStr);
                dto.setCreateAt(ts);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        dto.setEnabled(vo.getIs_enabled() == null ? true : false);
    }

}
