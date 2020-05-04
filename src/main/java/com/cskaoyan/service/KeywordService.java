package com.cskaoyan.service;

import com.cskaoyan.bean.mall.Keyword;

import java.util.List;

/**
 * @Author: Li Qing
 * @Create: 2020/4/24 15:37
 * @Version: 1.0
 */
public interface KeywordService {

    List<Keyword> queryKeywords(String keyword, String url, Integer page, Integer limit, String sort, String order);

    Integer createKeyword(Keyword keyword);

    Integer updateKeyword(Keyword keyword);

    Integer deleteKeyword(Keyword keyword);
}
