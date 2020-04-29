package com.cskaoyan.controller;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.mall.*;
import com.cskaoyan.service.MallService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public BaseRespVo getRegionList() {
        List result = mallService.queryRegions();
        return new BaseRespVo(0, result, "成功");
    }
}


