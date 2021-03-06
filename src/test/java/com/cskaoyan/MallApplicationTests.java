package com.cskaoyan;

import com.cskaoyan.bean.mall.Category;
import com.cskaoyan.bean.stat.OrderStat;
import com.cskaoyan.bean.system.Permission;
import com.cskaoyan.bean.user.Collect;
import com.cskaoyan.bean.user.SearchHistory;
import com.cskaoyan.bean.user.User;
import com.cskaoyan.mapper.*;
import com.cskaoyan.service.*;
import com.cskaoyan.utils.MD5Utils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@SpringBootTest
class MallApplicationTests {
    @Autowired
    UserService userService;
    @Autowired
    RegionService mallService;
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    OrderMapper orderMapper;

    @Test
    void contextLoads() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = userService.queryAddress(null, null, 10, 1, "add_time", "desc");
        List<User> users = userService.queryUsers(null, null, 10, 1, "add_time", "desc");
        String a = mapper.writeValueAsString(users);
        //String s = mapper.writeValueAsString(map);
        System.out.println(a);
    }

    @Test
    public void testCollectMapper() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        List<Collect> collects = userService.queryCollects(null, null, 10, 1, "add_time", "desc");
        System.out.println(mapper.writeValueAsString(collects));
    }

    @Test
    public void testHistory() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        List<SearchHistory> results = userService.queryHistory(null, null, 10, 1, "add_time", "desc");
        System.out.println(mapper.writeValueAsString(results));
    }

    @Test
    public void testRegions() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        List list = mallService.queryRegions();
        System.out.println(mapper.writeValueAsString(list));
    }

    @Test
    public void testUrl() throws FileNotFoundException {
        URL url = ResourceUtils.getURL("classpath:");
        System.out.println(url);
    }

    @Test
    public void testCategory() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        List<Category> list = categoryMapper.selectChildCategoriesByPid(0);
        System.out.println(mapper.writeValueAsString(list));
    }

    @Test
    public void testOrder() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        //List<Order> orders = mallService.queryOrders(null, null, new ArrayList<>(), 1, 20, "add_time", "desc");
        ArrayList<String> list = new ArrayList<>();
        list.add("aaa");
        System.out.println(mapper.writeValueAsString(list));
    }

    @Autowired
    GoodsService goodsService;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    PermissionMapper permissionMapper;

    @Test
    public void testgoods() throws JsonProcessingException {

        List<Permission> systemPermissions = permissionMapper.selectPermissionListByPid(0);
        System.out.println(systemPermissions);
    }

    @Test
    public void testOrderstat() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        List<OrderStat> list = orderMapper.selectOrderStat();
        System.out.println(mapper.writeValueAsString(list));

    }

    @Test
    public void testMD5() throws JsonProcessingException {
        String username = "admin123";
        String md5 = MD5Utils.getMd5("123");
        System.out.println(md5);
    }

    @Autowired
    ConfigService configService;
    @Autowired
    ConfigMapper configMapper;

    @Test
    public void testConfig() {
        //List<Config> mall = configMapper.selectSimpleConfig("\'%mall%\'");
        HashMap<String, String> config = configService.getConfig("mall");
        System.out.println(config);
    }

    @Autowired
    CouponMapper couponMapper;

    @Autowired
    Groupon_RulesMapper groupon_rulesMapper;
    @Autowired
    AdvertisementMapper advertisementMapper;
    @Autowired
    BrandMapper brandMapper;
    @Autowired
    TopicMapper topicMapper;
    @Test
    public void testHomeService() throws JsonProcessingException {
        //List<Goods> newGoodsList = goodsMapper.selectGoodsList4Wx(true, null);
        //List<Goods> hotGoodsList = goodsMapper.selectGoodsList4Wx(null, true);
        //List<Coupon> couponList = couponMapper.selectCouponList4Wx();
        //List<Category> channel = categoryMapper.selectParentCategoryList4Wx();
        //List<SimpleGroupon4Wx> grouponList = groupon_rulesMapper.selectGrouponList4Wx();
        ////banner的显示
        //AdvertisementExample example = new AdvertisementExample();
        //example.createCriteria().andDeletedEqualTo(false);
        //List<Advertisement> banner = advertisementMapper.selectByExample(example);
        ////brandList
        //List<Brand> brandList = brandMapper.selectBrandList4Wx();
        ////TopicList
        //List<Topic> topicList = topicMapper.selectTopicList4Wx();
        //List<Category4Wx> floorGoodsList = categoryMapper.selectFloorGoodsList4Wx();
        ////
        ObjectMapper mapper = new ObjectMapper();
    }
}
