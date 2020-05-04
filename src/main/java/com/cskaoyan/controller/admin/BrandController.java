package com.cskaoyan.controller.admin;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.mall.Brand;
import com.cskaoyan.service.BrandService;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Li Qing
 * @Create: 2020/4/25 12:38
 * @Version: 1.0
 */
@RestController
@RequestMapping("admin/brand")
public class BrandController {
    @Autowired
    BrandService brandService;

    @GetMapping("list")
    @RequiresPermissions("admin:brand:list")
    public BaseRespVo getBrandInfo(Integer id, String name, Integer page, Integer limit, String sort, String order) {
        List<Brand> list = brandService.queryBrands(id, name, page, limit, sort, order);
        Map<String, Object> result = new HashMap<>();
        long total = PageInfo.of(list).getTotal();
        result.put("items", list);
        result.put("total", total);
        return new BaseRespVo(0,result,"成功");
    }
    @RequiresPermissions("admin:brand:update")
    @PostMapping("update")
    public BaseRespVo updateBrand(@RequestBody Brand brand) {
        //System.out.println(brand);
        brandService.updateBrand(brand);
        BaseRespVo<Object> respVo = new BaseRespVo<>();
        respVo.setErrno(0);
        return new BaseRespVo(0,null,"成功");
    }

    @PostMapping("create")
    @RequiresPermissions("admin:brand:create")
    public BaseRespVo createBrand(@RequestBody Brand brand) {
        brandService.createBrand(brand);
        return new BaseRespVo(0,null,"成功");
    }

    @PostMapping("delete")
    @RequiresPermissions("admin:brand:delete")
    public BaseRespVo deleteBrand(@RequestBody Brand brand) {
        System.out.println(brand);
        brandService.deleteBrand(brand);
        BaseRespVo<Object> respVo = new BaseRespVo<>();
        respVo.setErrno(0);
        respVo.setErrmsg("成功");
        return respVo;
    }

}


