package com.cskaoyan.mapper;

import com.cskaoyan.bean.system.PermissionRole;
import com.cskaoyan.bean.system.PermissionRoleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface PermissionRoleMapper {
    long countByExample(PermissionRoleExample example);

    int deleteByExample(PermissionRoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PermissionRole record);

    int insertSelective(PermissionRole record);

    List<PermissionRole> selectByExample(PermissionRoleExample example);

    PermissionRole selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PermissionRole record, @Param("example") PermissionRoleExample example);

    int updateByExample(@Param("record") PermissionRole record, @Param("example") PermissionRoleExample example);

    int updateByPrimaryKeySelective(PermissionRole record);

    int updateByPrimaryKey(PermissionRole record);

    List<String> selectPermissionList(Integer roleId);

    Integer insertPermissionsByRoleId(@Param("roleId") Integer roleId, @Param("permissions") List permissions);

    Set<String> selectPermissions(@Param("roleIds") Set<Integer> roleIds);
}