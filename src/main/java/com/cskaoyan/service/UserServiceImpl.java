package com.cskaoyan.service;

import com.cskaoyan.bean.goods.GoodsExample;
import com.cskaoyan.bean.user.*;
import com.cskaoyan.mapper.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Li Qing
 * @Create: 2020/4/24 15:38
 * @Version: 1.0
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    AddressMapper addressMapper;
    @Autowired
    CollectMapper collectMapper;
    @Autowired
    RegionMapper regionMapper;
    @Autowired
    FootPrintMapper footPrintMapper;
    @Autowired
    SearchHistoryMapper searchHistoryMapper;
    @Autowired
    FeedbackMapper feedbackMapper;

    @Override
    public List<User> queryUsers(String username, String mobile, Integer page, Integer limit, String sort, String order) {
        PageHelper.startPage(page, limit, true);
        PageHelper.orderBy(sort + " " + order);
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        if (username != null) criteria.andUsernameLike("%" + username + "%");
        if (mobile != null) criteria.andMobileLike("%" + mobile + "%");
        return userMapper.selectByExample(example);
    }

    @Override
    public Map<String, Object> queryAddress(Integer userId, String name, Integer page, Integer limit, String sort, String order) {
        PageHelper.startPage(page, limit);
        ArrayList<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        AddressExample example = new AddressExample();
        example.setOrderByClause(sort + " " + order);
        AddressExample.Criteria criteria = example.createCriteria();
        if (userId != null) criteria.andUserIdEqualTo(userId);
        if (name != null) criteria.andNameLike("%" + name + "%");
        List<Address> addressList = addressMapper.selectByExample(example);
        long total = PageInfo.of(addressList).getTotal();
        map.put("total", total);
        for (Address address : addressList) {
            list.add(toVo(address));
        }
        map.put("items", list);
        return map;
    }

    /**
     * 完善地址信息
     *
     * @param address
     * @return
     */
    private Map<String, Object> toVo(Address address) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", address.getId());
        map.put("name", address.getName());
        map.put("userId", address.getUserId());
        map.put("province", selectRegionName(address.getProvinceId()));
        map.put("city", selectRegionName(address.getCityId()));
        map.put("area", selectRegionName(address.getAreaId()));
        map.put("address", address.getAddress());
        //map.put("areaCode",addressMapper.selectAreaCode(address.getAreaId()));
        map.put("mobile", address.getMobile());
        map.put("isDefault", address.getIsDefault());
        return map;
    }

    /**
     * 获取区域名字
     */
    private String selectRegionName(Integer code) {
        if (code == null) return null;
        return regionMapper.selectRegionName(code);
    }


    /**
     * 查询会员收藏信息
     *
     * @param userId
     * @param valueId
     * @param page
     * @param limit
     * @param sort
     * @param order
     * @return
     */
    @Override
    public List<Collect> queryCollects(Integer userId, Integer valueId, Integer page, Integer limit, String sort, String order) {
        PageHelper.startPage(page, limit);
        CollectExample example = new CollectExample();
        example.setOrderByClause(sort + " " + order);
        CollectExample.Criteria criteria = example.createCriteria();
        if (userId != null) criteria.andUserIdEqualTo(userId);
        if (valueId != null) criteria.andValueIdEqualTo(valueId);
        return collectMapper.selectByExample(example);
    }

    @Override
    public List<FootPrint> queryFootPrints(Integer userId, Integer goodsId, Integer page, Integer limit, String sort, String order) {
        PageHelper.startPage(page, limit);
        PageHelper.orderBy(sort + " " + order);
        FootPrintExample example = new FootPrintExample();
        FootPrintExample.Criteria criteria = example.createCriteria();
        if (userId != null) criteria.andUserIdEqualTo(userId);
        if (goodsId != null) criteria.andGoodsIdEqualTo(goodsId);
        return footPrintMapper.selectByExample(example);

    }

    /**
     * 查询用户搜索历史
     *
     * @param userId
     * @param keyword
     * @param page
     * @param limit
     * @param sort
     * @param order
     * @return
     */

    @Override
    public List<SearchHistory> queryHistory(Integer userId, String keyword, Integer page, Integer limit, String sort, String order) {
        PageHelper.startPage(page, limit);
        PageHelper.orderBy(sort + " " + order);
        SearchHistoryExample example = new SearchHistoryExample();
        SearchHistoryExample.Criteria criteria = example.createCriteria();
        if (userId != null) criteria.andUserIdEqualTo(userId);
        if (keyword != null) criteria.andKeywordLike("%" + keyword + "%");
        return searchHistoryMapper.selectByExample(example);
    }

    @Override
    public List<Feedback> queryFeedBacks(Integer id, String username, Integer page, Integer limit, String sort, String order) {
        PageHelper.startPage(page, limit);
        PageHelper.orderBy(sort + " " + order);
        FeedbackExample example = new FeedbackExample();
        FeedbackExample.Criteria criteria = example.createCriteria();
        if (id != null) criteria.andUserIdEqualTo(id);
        if (username != null) criteria.andUsernameLike("%" + username + "%");
        return feedbackMapper.selectByExample(example);
    }

    /**
     * 查询所有用户数目
     *
     * @return
     */
    @Override
    public Long queryTotalUsers() {
        UserExample example = new UserExample();
        example.createCriteria().andDeletedEqualTo(false);
        return userMapper.countByExample(example);
    }
}



