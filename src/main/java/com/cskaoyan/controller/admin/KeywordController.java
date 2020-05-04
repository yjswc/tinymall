package com.cskaoyan.controller.admin;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.mall.Keyword;
import com.cskaoyan.service.KeywordService;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * @Author: Li Qing
 * @Create: 2020/4/25 12:38
 * @Version: 1.0
 */
@RestController
@RequestMapping("admin/keyword")
public class KeywordController {
    @Autowired
    KeywordService keywordService;

    @GetMapping("list")
    @RequiresPermissions("admin:keyword:list")
    public BaseRespVo getKeyWords(String keyword, String url, Integer page, Integer limit, String sort, String order) {
        List<Keyword> list = keywordService.queryKeywords(keyword, url, page, limit, sort, order);
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
    @RequiresPermissions("admin:keyword:create")
    public BaseRespVo createKeyword(@RequestBody Keyword keyword) {
        keywordService.createKeyword(keyword);
        return new BaseRespVo(0, null, "成功");
    }

    /**
     * 更新关键字
     *
     * @param keyword
     * @return
     */
    @PostMapping("update")
    @RequiresPermissions("admin:keyword:update")
    public BaseRespVo updateKeyword(@RequestBody Keyword keyword) {
        keywordService.updateKeyword(keyword);
        return new BaseRespVo(0, null, "成功");

    }

    /**
     * 逻辑删除关键字
     *
     * @param keyword
     * @return
     */

    @PostMapping("delete")
    @RequiresPermissions("admin:keyword:delete")
    public BaseRespVo deleteKeyword(@RequestBody Keyword keyword) {
        keywordService.deleteKeyword(keyword);
        return new BaseRespVo(0, null, "成功");
    }

}


