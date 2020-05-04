package com.cskaoyan.service;

import com.cskaoyan.bean.promotion.Groupon_Rules;
import com.cskaoyan.bean.promotion.pojo.SimpleGroupon4Wx;

import java.util.List;
import java.util.Map;

/**
 * @Author: Li Qing
 * @Create: 2020/4/28 18:19
 * @Version: 1.0
 */
public interface GrouponService {
    abstract List<Groupon_Rules> queryGrouponRuleList(Integer goodsId, Integer page, Integer limit, String sort, String order);

    Integer updateGroupon_Rules(Groupon_Rules groupon_rules);

    Integer createGroupon_Rules(Groupon_Rules groupon_rules);

    Integer deleteGroupon_Rules(Groupon_Rules groupon_rules);

    Map<String, Object> queryGrouponListRecord(Integer goodsId, Integer page, Integer limit, String sort, String order);

    //==========================微信小程序接口分割线======================================//

    List<SimpleGroupon4Wx> queryGrouponList4Wx();
}
