package com.cskaoyan.service;

import com.cskaoyan.bean.promotion.Advertisement;

import java.util.List;

/**
 * @Author: Li Qing
 * @Create: 2020/4/28 14:27
 * @Version: 1.0
 */
public interface AdService {
    List<Advertisement> queryAdList(String name, String content, Integer page, Integer limit, String sort, String order);

    Integer updateAd(Advertisement advertisement);

    Integer createAd(Advertisement advertisement);

    Integer deleteAd(Advertisement advertisement);

    //===========================小程序接口分割线==================================//
    List<Advertisement> queryAdList4Wx();
}
