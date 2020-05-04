package com.cskaoyan.service;

import com.cskaoyan.bean.mall.Keyword;
import com.cskaoyan.bean.mall.KeywordExample;
import com.cskaoyan.mapper.KeywordMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * @Author: Li Qing
 * @Create: 2020/4/25 13:01
 * @Version: 1.0
 */
@Service
public class KeywordServiceImpl implements KeywordService {
    @Autowired
    KeywordMapper keywordMapper;


    @Override
    public List<Keyword> queryKeywords(String keyword, String url, Integer page, Integer limit, String sort, String order) {
        PageHelper.startPage(page, limit);
        PageHelper.orderBy(sort + " " + order);
        KeywordExample example = new KeywordExample();
        KeywordExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(keyword)) criteria.andKeywordLike("%" + keyword + "%");
        if (!StringUtils.isEmpty(url)) criteria.andUrlLike("%" + url + "%");
        criteria.andDeletedEqualTo(false);
        return keywordMapper.selectByExample(example);
    }

    /**
     * 创建关键字
     *
     * @param keyword
     * @return
     */

    @Override
    public Integer createKeyword(Keyword keyword) {

        Date date = new Date();
        keyword.setAddTime(date);
        keyword.setUpdateTime(date);
        return keywordMapper.insertSelective(keyword);

    }

    /**
     * 更新关键字
     *
     * @param keyword
     * @return
     */
    @Override
    public Integer updateKeyword(Keyword keyword) {
        keyword.setUpdateTime(new Date());
        return keywordMapper.updateByPrimaryKeySelective(keyword);
    }

    /**
     * 逻辑删除关键字
     *
     * @param keyword
     * @return
     */
    @Override
    public Integer deleteKeyword(Keyword keyword) {
        keyword.setIsDefault(true);
        keyword.setUpdateTime(new Date());
        return keywordMapper.updateByPrimaryKeySelective(keyword);
    }


}
