package com.cskaoyan.controller.admin;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.promotion.Groupon_Rules;
import com.cskaoyan.service.GrouponService;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Li Qing
 * @Create: 2020/4/28 18:18
 * @Version: 1.0
 */
@RestController
@RequestMapping("admin/groupon")
public class GrouponController {
    @Autowired
    GrouponService grouponService;

    @GetMapping("list")
    @RequiresPermissions("admin:groupon:list")
    public BaseRespVo getGroupon_RulesList(Integer goodsId, Integer page, Integer limit, String sort, String order) {
        List<Groupon_Rules> list = grouponService.queryGrouponRuleList(goodsId, page, limit, sort, order);
        Map<String, Object> result = new HashMap<>();
        long total = PageInfo.of(list).getTotal();
        result.put("total", total);
        result.put("items", list);
        return new BaseRespVo<>(0, result, "成功");
    }

    @PostMapping("update")
    @RequiresPermissions("admin:groupon:update")
    public BaseRespVo updateGroupon_Rules(@RequestBody Groupon_Rules groupon_rules) {
        grouponService.updateGroupon_Rules(groupon_rules);
        return new BaseRespVo<>(0, null, "成功");
    }

    @PostMapping("create")
    @RequiresPermissions("admin:groupon:create")
    public BaseRespVo createGroupon_Rules(@RequestBody Groupon_Rules groupon_rules) {
        grouponService.createGroupon_Rules(groupon_rules);
        return new BaseRespVo<>(0, null, "成功");
    }

    @PostMapping("delete")
    @RequiresPermissions("admin:groupon:delete")
    public BaseRespVo deleteGroupon_Rules(@RequestBody Groupon_Rules groupon_rules) {
        grouponService.deleteGroupon_Rules(groupon_rules);
        return new BaseRespVo<>(0, null, "成功");
    }

    @GetMapping("listRecord")
    @RequiresPermissions("admin:groupon:listRecord")
    public BaseRespVo getListRecord(Integer goodsId, Integer page, Integer limit, String sort, String order) {
        Map<String, Object> result = grouponService.queryGrouponListRecord(goodsId, page, limit, sort, order);
        return new BaseRespVo<>(0, result, "成功");
    }


}
