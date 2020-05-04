package com.cskaoyan.controller.admin;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.service.RegionService;
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
    RegionService regionService;

    @GetMapping("list")
    public BaseRespVo getRegionList() {
        List result = regionService.queryRegions();
        return new BaseRespVo(0, result, "成功");
    }
}


