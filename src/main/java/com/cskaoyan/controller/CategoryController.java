package com.cskaoyan.controller;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.mall.Category;
import com.cskaoyan.service.MallService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: Li Qing
 * @Create: 2020/4/25 12:38
 * @Version: 1.0
 */
@RestController
@RequestMapping("admin/category")
public class CategoryController {
    @Autowired
    MallService mallService;


    @GetMapping("list")
    @RequiresPermissions("admin:category:list")
    public BaseRespVo getCategories() {
        BaseRespVo<Object> respVo = new BaseRespVo<>();
        List<Category> result = mallService.queryCategories();
        return new BaseRespVo(0,result,"成功");
    }

    @PostMapping("update")
    @RequiresPermissions("admin:category:update")
    public BaseRespVo updateCategory(@RequestBody Category category) {
        mallService.updateCategory(category);
        return new BaseRespVo(0,null,"成功");
    }

    @PostMapping("create")
    @RequiresPermissions("admin:category:create")
    public BaseRespVo createCategory(@RequestBody Category category) {
        mallService.createCategory(category);
        return new BaseRespVo(0,null,"成功");
    }

    @PostMapping("delete")
    @RequiresPermissions("admin:category:delete")
    public BaseRespVo deleteCategory(@RequestBody Category category) {
        mallService.deleteCategory(category.getId());
        return new BaseRespVo(0,null,"成功");
    }

}


