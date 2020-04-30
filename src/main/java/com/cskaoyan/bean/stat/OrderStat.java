package com.cskaoyan.bean.stat;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;


/**
 * @Author: Li Qing
 * @Create: 2020/4/30 0:41
 * @Version: 1.0
 */
@Data
public class OrderStat {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date day;
    private Integer orders;
    private Integer customers;
    private Double pcr;
    private Double amount;

    public void setCustomers(Integer customers) {
        this.customers = customers;
        if (customers != null && amount != null) setPcr(amount / customers);
    }

    public void setAmount(Double amount) {
        this.amount = amount;
        if (customers != null && amount != null) setPcr(amount / customers);
    }
}
