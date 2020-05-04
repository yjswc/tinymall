package com.cskaoyan.controller.admin;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @Author: Li Qing
 * @Create: 2020/5/1 20:45
 * @Version: 1.0
 */
@RestController
@RequestMapping("admin/config")
public class ConfigController {
    @Autowired
    ConfigService configService;

    /**
     * 由于mall，express，order，wx的数据类型都是config类，直接用map接收统一更新即可
     *
     * @param map
     * @return
     */
    @PostMapping("express")
    public BaseRespVo updateExpressConfig(@RequestBody HashMap<String, String> map) {
        configService.updateConfig(map);
        return new BaseRespVo(0, null, "成功");
    }

    @GetMapping("express")
    public BaseRespVo getExpressConfig() {
        HashMap<String, String> result = configService.getConfig("express");
        return new BaseRespVo(0, result, "成功");
    }

    @PostMapping("mall")
    public BaseRespVo updateMallConfig(@RequestBody HashMap<String, String> map) {
        configService.updateConfig(map);
        return new BaseRespVo(0, null, "成功");
    }

    @GetMapping("mall")
    public BaseRespVo getMallConfig() {
        HashMap<String, String> result = configService.getConfig("mall");
        return new BaseRespVo(0, result, "成功");
    }

    @PostMapping("order")
    public BaseRespVo updateOrderConfig(@RequestBody HashMap<String, String> map) {
        configService.updateConfig(map);
        return new BaseRespVo(0, null, "成功");
    }

    @GetMapping("order")
    public BaseRespVo getOrderConfig() {
        HashMap<String, String> result = configService.getConfig("order");
        return new BaseRespVo(0, result, "成功");
    }

    @PostMapping("wx")
    public BaseRespVo updateWXConfig(@RequestBody HashMap<String, String> map) {
        configService.updateConfig(map);
        return new BaseRespVo(0, null, "成功");
    }

    @GetMapping("wx")
    public BaseRespVo getWXConfig() {
        HashMap<String, String> result = configService.getConfig("wx");
        return new BaseRespVo(0, result, "成功");
    }
}
