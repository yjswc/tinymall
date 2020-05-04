package com.cskaoyan.bean.promotion.pojo;

import com.cskaoyan.bean.goods.Goods;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: Li Qing
 * @Create: 2020/5/3 14:40
 * @Version: 1.0
 */
@Data
public class SimpleGroupon4Wx {
    private Integer id;
    private Integer goodsId;
    private BigDecimal groupon_price;
    private Integer groupon_member;
    private BigDecimal discount;
    private Goods goods;

    //触发计算折扣后的价格
    public void setDiscount(BigDecimal discount) {
        if (groupon_price == null && goods != null)
            setGroupon_price(goods.getRetailPrice().multiply(discount).divide(new BigDecimal(100)));
        this.discount = discount;
    }

    public void setGoods(Goods goods) {
        if (groupon_price == null && discount != null)
            setGroupon_price(goods.getRetailPrice().multiply(discount).divide(new BigDecimal(100)));
        this.goods = goods;
    }
}
