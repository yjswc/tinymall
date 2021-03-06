package com.cskaoyan.service;

import com.cskaoyan.bean.goods.Goods;
import com.cskaoyan.bean.goods.GoodsInTotal;

import java.util.List;
import java.util.Map;

/**
 * @Author: Li Qing
 * @Create: 2020/4/27 11:14
 * @Version: 1.0
 */
public interface GoodsService {
    List<Goods> queryGoodsList(Integer goodsId, String goodSn, String name, Integer page, Integer limit, String sort, String order);

    Map<String,Object> queryCatAndBrand();

    Map<String, Object> queryGoodsDetail(Integer goodsId);

    Integer updateGoods(GoodsInTotal goodsIntotal);

    Integer createGoods(GoodsInTotal goodsIntotal);

    Integer deleteGoods(Goods goods);

    Long queryTotalGoods();

    Long queryTotalProducts();
    //======================微信小程序相关接口=================================//
    List<Goods> queryGoodsList4Wx(Boolean isNew,Boolean isHot);
}
