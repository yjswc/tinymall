package com.cskaoyan.service;

import com.cskaoyan.bean.mall.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: Li Qing
 * @Create: 2020/4/24 15:37
 * @Version: 1.0
 */
public interface MallService {

    List queryRegions();

    List<Brand> queryBrands(Integer id, String name, Integer page, Integer limit, String sort, String order);

    Integer updateBrand(Brand brand);

    Integer createBrand(Brand brand);

    Integer deleteBrand(Brand brand);

    List<Category> queryCategories();

    Integer updateCategory(Category category);

    Integer createCategory(Category category);

    Integer deleteCategory(Integer id);

    List<Order> queryOrders(Integer id, Integer userId, ArrayList<Short> orderStatusArray,
                            Integer page, Integer limit, String sort, String order);

    Map queryOrderDetail(Integer id);

    Integer createIssue(Issue issue);

    List<Issue> queryIssues(String question, Integer page, Integer limit, String sort, String order);

    Integer updateIssue(Issue issue);

    Integer deleteIssue(Issue issue);


    List<Keyword> queryKeywords(String keyword, String url, Integer page, Integer limit, String sort, String order);

    Integer createKeyword(Keyword keyword);

    Integer updateKeyword(Keyword keyword);

    Integer deleteKeyword(Keyword keyword);

    Integer deleteOrder(Integer orderId);

}
