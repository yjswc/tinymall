package com.cskaoyan.controller;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.goods.Goods;
import com.cskaoyan.bean.goods.GoodsInTotal;
import com.cskaoyan.service.GoodsService;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Li Qing
 * @Create: 2020/4/27 11:14
 * @Version: 1.0
 * <p>
 * 商品管理模块
 */
@RestController
@RequestMapping("admin/goods")
public class GoodsController {

    @Autowired
    GoodsService goodsService;

    @GetMapping("list")
    @RequiresPermissions("admin:goods:list")
    public BaseRespVo getGoodsList(Integer goodsId, String goodSn, String name, Integer page, Integer limit, String sort, String order) {
        List<Goods> list = goodsService.queryGoodsList(goodsId, goodSn, name, page, limit, sort, order);
        long total = PageInfo.of(list).getTotal();
        HashMap<String, Object> result = new HashMap<>();
        result.put("total", total);
        result.put("items", list);
        return new BaseRespVo(0, result, "成功");
    }

    /**
     * 查询所有的品牌和类目信息
     *
     * @return
     */
    @GetMapping("catAndBrand")
    @RequiresPermissions("admin:goods:catAndBrand")
    public BaseRespVo getCatAndBrand() {
        Map result = goodsService.queryCatAndBrand();
        return new BaseRespVo(0, result, "成功");
    }

    @GetMapping("detail")
    @RequiresPermissions("admin:goods:detail")
    public BaseRespVo getGoodsDetail(Integer id) {
        Map<String, Object> result = goodsService.queryGoodsDetail(id);
        return new BaseRespVo(0, result, "成功");
    }


    @PostMapping("update")
    @RequiresPermissions("admin:goods:update")
    public BaseRespVo updateGoods(@RequestBody GoodsInTotal goodsInTotal) {
        goodsService.updateGoods(goodsInTotal);
        return new BaseRespVo(0, null, "成功");
    }

    @PostMapping("create")
    @RequiresPermissions("admin:goods:create")
    public BaseRespVo createGoods(@RequestBody GoodsInTotal goodsInTotal) {
        goodsService.createGoods(goodsInTotal);
        return new BaseRespVo(0, null, "成功");
    }

    @PostMapping("delete")
    @RequiresPermissions("admin:goods:delete")
    public BaseRespVo deleteGoods(@RequestBody Goods goods) {
        goodsService.deleteGoods(goods);
        return new BaseRespVo(0, null, "成功");
    }

}
