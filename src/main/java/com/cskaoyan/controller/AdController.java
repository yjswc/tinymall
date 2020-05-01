package com.cskaoyan.controller;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.promotion.Advertisement;
import com.cskaoyan.service.AdService;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Li Qing
 * @Create: 2020/4/28 14:26
 * @Version: 1.0
 */
@RestController
@RequestMapping("admin/ad")
public class AdController {

    @Autowired
    AdService adService;

    @GetMapping("list")
    @RequiresPermissions("admin:ad:list")
    public BaseRespVo getAdList(String name, String content, Integer page, Integer limit, String sort, String order) {
        List<Advertisement> list = adService.queryAdList(name, content, page, limit, sort, order);
        Map<String, Object> result = new HashMap<>();
        long total = PageInfo.of(list).getTotal();
        result.put("total", total);
        result.put("items", list);
        return new BaseRespVo<>(0, result, "成功");
    }

    @PostMapping("update")
    @RequiresPermissions("admin:ad:update")
    public BaseRespVo updateAd(@RequestBody Advertisement advertisement) {
        adService.updateAd(advertisement);
        return new BaseRespVo<>(0, null, "成功");
    }

    @RequiresPermissions("admin:ad:create")
    @PostMapping("create")
    public BaseRespVo createAd(@RequestBody Advertisement advertisement) {
        adService.createAd(advertisement);
        return new BaseRespVo<>(0, null, "成功");
    }

    @RequiresPermissions("admin:ad:delete")
    @PostMapping("delete")
    public BaseRespVo deleteAd(@RequestBody Advertisement advertisement) {
        adService.deleteAd(advertisement);
        return new BaseRespVo<>(0, null, "成功");
    }
}
