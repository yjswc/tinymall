package com.cskaoyan.service;

import com.cskaoyan.bean.promotion.Advertisement;
import com.cskaoyan.bean.promotion.AdvertisementExample;
import com.cskaoyan.mapper.AdvertisementMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * @Author: Li Qing
 * @Create: 2020/4/28 14:28
 * @Version: 1.0
 */
@Service
public class AdServiceImpl implements AdService {
    @Autowired
    AdvertisementMapper advertisementMapper;

    @Override
    public List<Advertisement> queryAdList(String name, String content, Integer page, Integer limit, String sort, String order) {
        PageHelper.startPage(page, limit);
        PageHelper.orderBy(sort + " " + order);
        AdvertisementExample example = new AdvertisementExample();
        AdvertisementExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(name)) criteria.andNameLike("%" + name + "%");
        if (!StringUtils.isEmpty(content)) criteria.andContentLike("%" + content + "%");
        criteria.andDeletedEqualTo(false);
        return advertisementMapper.selectByExample(example);
    }

    @Override
    public Integer updateAd(Advertisement advertisement) {
        Date date = new Date();
        advertisement.setUpdateTime(date);
        if (advertisement.getEnabled() && advertisement.getStartTime() == null) advertisement.setStartTime(date);
        if (!advertisement.getEnabled() && advertisement.getStartTime() != null) advertisement.setEndTime(date);
        return advertisementMapper.updateByPrimaryKeySelective(advertisement);
    }

    @Override
    public Integer createAd(Advertisement advertisement) {
        Date date = new Date();
        advertisement.setAddTime(date);
        advertisement.setUpdateTime(date);
        if (advertisement.getEnabled()) advertisement.setStartTime(date);
        return advertisementMapper.insertSelective(advertisement);
    }

    @Override
    public Integer deleteAd(Advertisement advertisement) {
        Date date = new Date();
        advertisement.setUpdateTime(date);
        advertisement.setDeleted(true);
        return advertisementMapper.updateByPrimaryKeySelective(advertisement);
    }
}
