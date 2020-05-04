package com.cskaoyan.bean.mall;

import lombok.Data;

@Data
public class Region {
    private Integer id;

    private Integer pid;

    private String name;

    private Byte type;

    private Integer code;

    //private List<Region> children;

}