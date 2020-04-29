package com.cskaoyan.bean.mall;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data

public class OrderGoods {
    private Integer id;

    private Integer orderId;

    private Integer goodsId;

    private String goodsName;

    private String goodsSn;

    private Integer productId;

    private Short number;

    private BigDecimal price;

    private List specifications;

    private String picUrl;

    private Integer comment;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JsonIgnore
    private Date addTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JsonIgnore
    private Date updateTime;

    private Boolean deleted;
}