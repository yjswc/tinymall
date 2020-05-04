package com.cskaoyan.bean.mall.pojo;

import com.cskaoyan.bean.goods.Goods;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

/**
 * @Author: Li Qing
 * @Create: 2020/5/3 17:06
 * @Version: 1.0
 * 用于wx/home/index页面返回数据
 */
@Data
public class Category4Wx {
    private Integer id;
    private String name;
    @JsonIgnore
    private List<Integer> childCategoryList;
    private List<Goods> goodsList;
}
