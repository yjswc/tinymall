package com.cskaoyan.bean.stat;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @Author: Li Qing
 * @Create: 2020/4/30 0:42
 * @Version: 1.0
 */
@Data
public class GoodsStat {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date day;
    private Integer orders;
    private Integer products;
    private Double amount;
}
