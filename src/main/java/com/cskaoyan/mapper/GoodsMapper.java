package com.cskaoyan.mapper;

import com.cskaoyan.bean.goods.Goods;
import com.cskaoyan.bean.goods.GoodsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsMapper {
    long countByExample(GoodsExample example);

    int deleteByExample(GoodsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Goods record);

    int insertSelective(Goods record);

    List<Goods> selectByExampleWithBLOBs(GoodsExample example);

    List<Goods> selectByExample(GoodsExample example);

    Goods selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Goods record, @Param("example") GoodsExample example);

    int updateByExampleWithBLOBs(@Param("record") Goods record, @Param("example") GoodsExample example);

    int updateByExample(@Param("record") Goods record, @Param("example") GoodsExample example);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKeyWithBLOBs(Goods record);

    int updateByPrimaryKey(Goods record);

    //===========================微信小程序相关分割线=====================================//
    List<Goods> selectGoodsList4Wx(@Param("isNew") Boolean isNew, @Param("isHot") Boolean isHot, @Param("limit") Integer limit);

    //按商品编号找商品信息
    Goods selectGoodsById4Wx(Integer id);

    //按货物类目编号表返回的信息
    List<Goods> selectGoodsByCategoryIds4Wx(@Param("categoryIds") List<Integer> categoryIds, @Param("limit") Integer limit);

}