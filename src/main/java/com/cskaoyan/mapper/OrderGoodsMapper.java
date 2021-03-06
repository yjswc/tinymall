package com.cskaoyan.mapper;

import com.cskaoyan.bean.mall.OrderGoods;
import com.cskaoyan.bean.mall.OrderGoodsExample;
import com.cskaoyan.bean.stat.GoodsStat;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderGoodsMapper {
    long countByExample(OrderGoodsExample example);

    int deleteByExample(OrderGoodsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OrderGoods record);

    int insertSelective(OrderGoods record);

    List<OrderGoods> selectByExample(OrderGoodsExample example);

    OrderGoods selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OrderGoods record, @Param("example") OrderGoodsExample example);

    int updateByExample(@Param("record") OrderGoods record, @Param("example") OrderGoodsExample example);

    int updateByPrimaryKeySelective(OrderGoods record);

    int updateByPrimaryKey(OrderGoods record);

    //<!--无效状态102，103，203不列入统计，删除订单不列入统计-->
    List<GoodsStat> selectOrderGoodsStat();
}