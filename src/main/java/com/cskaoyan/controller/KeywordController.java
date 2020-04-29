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
@RequestMapping("admin/keyword")
public class KeywordController {
    @Autowired
    MallService mallService;

    @GetMapping("list")
    public BaseRespVo getKeyWords(String keyword, String url, Integer page, Integer limit, String sort, String order) {
        List<Keyword> list = mallService.queryKeywords(keyword, url, page, limit, sort, order);
        long total = PageInfo.of(list).getTotal();
        HashMap<String, Object> result = new HashMap<>();
        result.put("total", total);
        result.put("items", list);
        return new BaseRespVo(0, result, "成功");
    }

    /**
     * 创建关键字
     *
     * @param keyword
     * @return
     */

    @PostMapping("create")
    public BaseRespVo createKeyword(@RequestBody Keyword keyword) {
        mallService.createKeyword(keyword);
        return new BaseRespVo(0, null, "成功");
    }

    /**
     * 更新关键字
     *
     * @param keyword
     * @return
     */
    @PostMapping("update")
    public BaseRespVo updateKeyword(@RequestBody Keyword keyword) {
        mallService.updateKeyword(keyword);
        return new BaseRespVo(0, null, "成功");

    }

    /**
     * 逻辑删除关键字
     *
     * @param keyword
     * @return
     */

    @PostMapping("delete")
    public BaseRespVo deleteKeyword(@RequestBody Keyword keyword) {
        mallService.deleteKeyword(keyword);
        return new BaseRespVo(0, null, "成功");
    }

}


