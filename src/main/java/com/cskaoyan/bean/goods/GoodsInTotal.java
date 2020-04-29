package com.cskaoyan.bean.goods;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

/**
 * @Author: Li Qing
 * @Create: 2020/4/28 10:17
 * @Version: 1.0
 * 商品信息汇总类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GoodsInTotal {
    private Goods goods;
    private ArrayList<Attribute> attributes;
    private ArrayList<Product> products;
    private ArrayList<Specification> specifications;
}
