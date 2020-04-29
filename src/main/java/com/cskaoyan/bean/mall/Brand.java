package com.cskaoyan.bean.mall;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Brand {
    private Integer id;

    private String name;

    private String desc;

    private String picUrl;

    private Byte sortOrder;

    private BigDecimal floorPrice;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JsonIgnore
    private Date addTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JsonIgnore
    private Date updateTime;
    @JsonIgnore
    private Boolean deleted;

}