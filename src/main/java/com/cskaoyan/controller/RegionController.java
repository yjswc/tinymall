package com.cskaoyan.controller;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.service.MallService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: Li Qing
 * @Create: 2020/4/25 12:38
 * @Version: 1.0
 */
@RestController
@RequestMapping("admin/region")
public class RegionController {
    @Autowired
    MallService mallService;

    @GetMapping("list")
    @RequiresPermissions("admin:region:list")
    public BaseRespVo getRegionList() {
        List result = mallService.queryRegions();
        return new BaseRespVo(0, result, "成功");
    }
}


