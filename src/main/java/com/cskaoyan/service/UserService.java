package com.cskaoyan.service;

import com.cskaoyan.bean.user.*;

import java.util.List;
import java.util.Map;

/**
 * @Author: Li Qing
 * @Create: 2020/4/24 15:37
 * @Version: 1.0
 */
public interface UserService {
    List<User> queryUsers(String username, String mobile, Integer pageNum, Integer pageSize, String sort, String order);

    Map<String, Object> queryAddress(Integer userId, String name, Integer page, Integer limit, String sort, String order);

    List<Collect> queryCollects(Integer userId, Integer valueId, Integer page, Integer limit, String sort, String order);

    List<FootPrint> queryFootPrints(Integer userId, Integer goodsId, Integer page, Integer limit, String sort, String order);

    List<SearchHistory> queryHistory(Integer userId, String keyword, Integer page, Integer limit, String sort, String order);

    List<Feedback> queryFeedBacks(Integer id, String username, Integer page, Integer limit, String sort, String order);

    Long queryTotalUsers();
}
