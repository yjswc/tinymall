package com.cskaoyan.controller;

import com.cskaoyan.bean.BaseRespVo;

import com.cskaoyan.service.MallService;

import com.cskaoyan.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import java.util.HashMap;

import java.util.Map;

/**
 * @Author: Li Qing
 * @Create: 2020/4/25 12:38
 * @Version: 1.0
 */
@RestController
@RequestMapping("admin/storage")
public class StorageController {
    @Autowired
    StorageService storageService;

    @PostMapping("create")
    public BaseRespVo createStorage(MultipartFile file) {
        String url = null;
        try {
            url = storageService.createStorage(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, Object> result = new HashMap<>();
        result.put("url", url);
        return new BaseRespVo(0, result, "成功");
    }


}


