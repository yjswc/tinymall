package com.cskaoyan.mapper;

import com.cskaoyan.bean.config.Config;
import com.cskaoyan.bean.config.ConfigExample;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

public interface ConfigMapper {
    long countByExample(ConfigExample example);

    int deleteByExample(ConfigExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Config record);

    int insertSelective(Config record);

    List<Config> selectByExample(ConfigExample example);

    Config selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Config record, @Param("example") ConfigExample example);

    int updateByExample(@Param("record") Config record, @Param("example") ConfigExample example);

    int updateByPrimaryKeySelective(Config record);

    int updateByPrimaryKey(Config record);

    /**
     * 批量更新设置（假设已经创建了所有的keyName，不存在插入操作）
     *
     * @param configs
     * @return
     */
    Integer insertOrUpdateConfigByBatch(@Param("configs") ArrayList<Config> configs);

}