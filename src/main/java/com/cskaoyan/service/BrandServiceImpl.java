package com.cskaoyan.service;

import com.cskaoyan.bean.mall.Brand;
import com.cskaoyan.bean.mall.BrandExample;
import com.cskaoyan.mapper.BrandMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * @Author: Li Qing
 * @Create: 2020/5/4 17:24
 * @Version: 1.0
 */
@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    BrandMapper brandMapper;

    @Override
    public List<Brand> queryBrands(Integer id, String name, Integer page, Integer limit, String sort, String order) {
        PageHelper.startPage(page, limit);
        PageHelper.orderBy(sort + " " + order);
        BrandExample example = new BrandExample();
        BrandExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(name)) criteria.andNameLike("%" + name + "%");
        if (id != null) criteria.andIdEqualTo(id);
        if (id != null) criteria.andIdEqualTo(id);
        criteria.andDeletedEqualTo(false);
        return brandMapper.selectByExample(example);
    }


    @Override
    public Integer updateBrand(Brand brand) {
        brand.setUpdateTime(new Date());
        return brandMapper.updateByPrimaryKeySelective(brand);
    }

    @Override
    public Integer createBrand(Brand brand) {
        Date date = new Date();
        brand.setAddTime(date);
        brand.setUpdateTime(date);
        return brandMapper.insertSelective(brand);
    }

    @Override
    public Integer deleteBrand(Brand brand) {
        brand.setUpdateTime(new Date());
        System.out.println(brand.getUpdateTime());
        brand.setDeleted(true);
        return brandMapper.updateByPrimaryKeySelective(brand);
    }

    //===========================微信小程序分割线============================//

    @Override
    public List<Brand> queryBrandList4Wx() {
        return brandMapper.selectBrandList4Wx(6);
    }

    ;

}
