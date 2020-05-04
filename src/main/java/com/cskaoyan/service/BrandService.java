package com.cskaoyan.service;

import com.cskaoyan.bean.mall.Brand;

import java.util.List;

/**
 * @Author: Li Qing
 * @Create: 2020/5/4 17:23
 * @Version: 1.0
 */
public interface BrandService {
    List<Brand> queryBrands(Integer id, String name, Integer page, Integer limit, String sort, String order);

    Integer updateBrand(Brand brand);

    Integer createBrand(Brand brand);

    Integer deleteBrand(Brand brand);

    //===========================微信小程序分割线============================//
    List<Brand> queryBrandList4Wx();
}
