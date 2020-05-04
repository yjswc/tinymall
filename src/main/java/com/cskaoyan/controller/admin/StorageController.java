package com.cskaoyan.controller.admin;

import com.cskaoyan.bean.BaseRespVo;


import com.cskaoyan.bean.system.Storage;
import com.cskaoyan.service.StorageService;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import java.util.HashMap;

import java.util.List;
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
    @RequiresPermissions("admin:storage:list")
    @GetMapping("list")
    public BaseRespVo getStorageList(String key, String name, Integer page, Integer limit, String sort, String order) {
        List<Storage> list = storageService.queryStorageList(key, name, page, limit, sort, order);
        Map<String, Object> result = new HashMap<>();
        long total = PageInfo.of(list).getTotal();
        result.put("total", total);
        result.put("items", list);
        return new BaseRespVo<>(0, result, "成功");
    }

    @RequiresPermissions("admin:storage:create")
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
    @RequiresPermissions("admin:storage:update")
    @PostMapping("update")
    public BaseRespVo updateStorage(@RequestBody Storage storage) {
        storageService.updateStorage(storage);
        return new BaseRespVo<>(0, null, "成功");
    }
    @RequiresPermissions("admin:storage:delete")
    @PostMapping("delete")
    public BaseRespVo deleteStorage(@RequestBody Storage storage) {
        storageService.deleteStorage(storage);
        return new BaseRespVo<>(0, null, "成功");
    }


}


