package com.cskaoyan.service;


import com.cskaoyan.bean.goods.Goods;
import com.cskaoyan.bean.promotion.Groupon;
import com.cskaoyan.bean.promotion.GrouponExample;
import com.cskaoyan.bean.promotion.Groupon_Rules;
import com.cskaoyan.bean.promotion.Groupon_RulesExample;
import com.cskaoyan.bean.promotion.pojo.SimpleGroupon4Wx;
import com.cskaoyan.mapper.GoodsMapper;
import com.cskaoyan.mapper.GrouponMapper;
import com.cskaoyan.mapper.Groupon_RulesMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: Li Qing
 * @Create: 2020/4/28 18:19
 * @Version: 1.0
 */
@Service
public class GrouponServiceImpl implements GrouponService {
    @Autowired
    GrouponMapper grouponMapper;
    @Autowired
    Groupon_RulesMapper groupon_rulesMapper;
    @Autowired
    GoodsMapper goodsMapper;

    @Override
    public List<Groupon_Rules> queryGrouponRuleList(Integer goodsId, Integer page, Integer limit, String sort, String order) {
        PageHelper.startPage(page, limit);
        PageHelper.orderBy(sort + " " + order);
        Groupon_RulesExample example = new Groupon_RulesExample();
        Groupon_RulesExample.Criteria criteria = example.createCriteria();
        if (goodsId != null) criteria.andGoodsIdEqualTo(goodsId);
        criteria.andDeletedEqualTo(false);
        return groupon_rulesMapper.selectByExample(example);
    }

    @Override
    public Integer updateGroupon_Rules(Groupon_Rules groupon_rules) {
        Integer goodsId = groupon_rules.getGoodsId();
        Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
        //商品不存在
        if (goods == null) return 301;
        groupon_rules.setGoodsName(goods.getName());
        Date date = new Date();
        groupon_rules.setUpdateTime(date);
        return groupon_rulesMapper.updateByPrimaryKeySelective(groupon_rules);
    }

    @Override
    public Integer createGroupon_Rules(Groupon_Rules groupon_rules) {
        Integer goodsId = groupon_rules.getGoodsId();
        Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
        //商品不存在
        if (goods == null) return 301;
        groupon_rules.setGoodsName(goods.getName());
        Date date = new Date();
        groupon_rules.setAddTime(date);
        groupon_rules.setUpdateTime(date);
        return groupon_rulesMapper.insertSelective(groupon_rules);
    }

    @Override
    public Integer deleteGroupon_Rules(Groupon_Rules groupon_rules) {
        Date date = new Date();
        groupon_rules.setUpdateTime(date);
        groupon_rules.setDeleted(true);
        return groupon_rulesMapper.updateByPrimaryKeySelective(groupon_rules);
    }


    @Override
    public Map<String, Object> queryGrouponListRecord(Integer goodsId, Integer page, Integer limit, String sort, String order) {
        //if (goodsId!=null) queryGrouponRul
        Integer rulesId = 1;
        List<Groupon> grouponList = queryselective(rulesId, page, limit, sort, order);
        ArrayList<String> records = new ArrayList<>();
        for (Groupon groupon : grouponList) {
            try {

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private List<Groupon> queryselective(Integer rulesId, Integer page, Integer limit, String sort, String order) {
        PageHelper.startPage(page, limit);
        PageHelper.orderBy(sort + " " + order);
        GrouponExample example = new GrouponExample();
        GrouponExample.Criteria criteria = example.createCriteria();
        if (rulesId != null) criteria.andRulesIdEqualTo(rulesId);

        return null;

    }

    private List<Groupon> queryGrouponList(Integer grouponId, Integer page, Integer limit, String sort, String order) {
        PageHelper.startPage(page, limit);
        PageHelper.orderBy(sort + " " + order);
        GrouponExample example = new GrouponExample();

        GrouponExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedEqualTo(false).andPayedEqualTo(true);
        if (grouponId != null) criteria.andGrouponIdEqualTo(grouponId);
        return grouponMapper.selectByExample(example);
    }
    //==========================微信小程序接口分割线======================================//

    @Override
    public List<SimpleGroupon4Wx> queryGrouponList4Wx() {
        return groupon_rulesMapper.selectGrouponList4Wx(6);
    }
}
