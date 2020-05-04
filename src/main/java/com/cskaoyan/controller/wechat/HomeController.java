package com.cskaoyan.controller.wechat;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.goods.Goods;
import com.cskaoyan.bean.mall.Brand;
import com.cskaoyan.bean.mall.Category;
import com.cskaoyan.bean.mall.pojo.Category4Wx;
import com.cskaoyan.bean.promotion.Advertisement;
import com.cskaoyan.bean.promotion.Topic;
import com.cskaoyan.bean.promotion.pojo.SimpleGroupon4Wx;
import com.cskaoyan.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Li Qing
 * @Create: 2020/5/3 20:30
 * @Version: 1.0
 */
@RestController
@RequestMapping("wx/home")
public class HomeController {

    @Autowired
    GoodsService goodsService;
    @Autowired
    CouponService couponService;
    @Autowired
    GrouponService grouponService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    AdService adService;
    @Autowired
    BrandService brandService;
    @Autowired
    TopicService topicService;

    @GetMapping("index")
    public BaseRespVo getHomeIndex() {
        Map<String, Object> result = new HashMap<>();
        List<Goods> newGoodsList = goodsService.queryGoodsList4Wx(true, null);
        List<Goods> hotGoodsList = goodsService.queryGoodsList4Wx(null, true);
        List<SimpleGroupon4Wx> couponList = couponService.queryCouponList4Wx();
        List<Category> channel = categoryService.queryParentCategoryList4Wx();
        List<SimpleGroupon4Wx> grouponList = grouponService.queryGrouponList4Wx();
        List<Advertisement> banner = adService.queryAdList4Wx();
        List<Brand> brandList = brandService.queryBrandList4Wx();
        List<Topic> topicList = topicService.queryTopicList4Wx();
        List<Category4Wx> floorGoodsList = categoryService.queryFloorGoodsList4Wx();
        result.put("newGoodsList", newGoodsList);
        result.put("hotGoodsList", hotGoodsList);
        result.put("couponList", couponList);
        result.put("channel", channel);
        result.put("grouponList", grouponList);
        result.put("banner", banner);
        result.put("brandList", brandList);
        result.put("topicList", topicList);
        result.put("floorGoodsList", floorGoodsList);
        return new BaseRespVo(0, result, "成功");
    }
}
