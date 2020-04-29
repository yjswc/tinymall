package com.cskaoyan.bean.goods;

import lombok.Data;

import java.util.List;

/**
 * @Author: Li Qing
 * @Create: 2020/4/27 17:12
 * @Version: 1.0
 * 简单类目信息
 */
@Data
public class SimpleCategory {
    private Integer value;
    private String label;
    private List<SimpleCategory> children;
}
