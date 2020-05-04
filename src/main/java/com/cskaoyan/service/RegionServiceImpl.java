package com.cskaoyan.service;

import com.cskaoyan.bean.mall.*;
import com.cskaoyan.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Author: Li Qing
 * @Create: 2020/4/25 13:01
 * @Version: 1.0
 */
@Service
public class RegionServiceImpl implements RegionService {

    @Autowired
    RegionMapper regionMapper;

    @Override
    public List<Object> queryRegions() {
        //long startTime = System.currentTimeMillis();
        List<Object> regions = toVo(0);
        //long endTime = System.currentTimeMillis();
        //System.out.println(endTime-startTime);
        return regions;
    }

    /**
     * 查询全国省市区，分级
     *
     * @param pid
     * @return
     */

    private List<Region> generateRegionList(Integer pid) {
        RegionExample example = new RegionExample();
        if (pid != null) example.createCriteria().andPidEqualTo(pid);
        return regionMapper.selectByExample(example);
    }

    private List<Object> toVo(Integer pid) {
        ArrayList<Object> list = new ArrayList<>();
        List<Region> regions = generateRegionList(pid);
        for (Region region : regions) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("id", region.getId());
            map.put("name", region.getName());
            map.put("type", region.getType());
            map.put("code", region.getCode());
            if (region.getId() < 100) map.put("children", toVo(region.getId()));
            list.add(map);
        }
        return list;
    }


}
