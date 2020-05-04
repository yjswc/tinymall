package com.cskaoyan.controller.admin;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.system.Role;
import com.cskaoyan.bean.system.RoleOption;
import com.cskaoyan.bean.system.UpdatePermissionBean;
import com.cskaoyan.service.RoleService;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Li Qing
 * @Create: 2020/4/29 8:54
 * @Version: 1.0
 */
@RestController
@RequestMapping("admin/role")
public class RoleController {
    @Autowired
    RoleService roleService;

    @GetMapping("options")
    @RequiresPermissions("admin:role:options")
    public BaseRespVo getRolesOption() {
        List<RoleOption> result = roleService.queryRoleOptionList();
        return new BaseRespVo<>(0, result, "成功");
    }

    @GetMapping("list")
    @RequiresPermissions("admin:role:list")
    public BaseRespVo getRoleList(String name, Integer page, Integer limit, String sort, String order) {
        List<Role> list = roleService.queryRoleList(name, page, limit, sort, order);
        Map<String, Object> result = new HashMap<>();
        long total = PageInfo.of(list).getTotal();
        result.put("total", total);
        result.put("items", list);
        return new BaseRespVo<>(0, result, "成功");
    }

    @PostMapping("update")
    @RequiresPermissions("admin:role:update")
    public BaseRespVo updateAd(@RequestBody Role role) {
        roleService.updateRole(role);
        return new BaseRespVo<>(0, null, "成功");
    }

    @PostMapping("create")
    @RequiresPermissions("admin:role:create")
    public BaseRespVo createAd(@RequestBody Role role) {
        roleService.createRole(role);
        return new BaseRespVo<>(0, null, "成功");
    }

    @PostMapping("delete")
    @RequiresPermissions("admin:role:delete")
    public BaseRespVo deleteAd(@RequestBody Role role) {
        roleService.deleteRole(role);
        return new BaseRespVo<>(0, null, "成功");
    }


    @GetMapping("permissions")
    @RequiresPermissions("admin:role:permissions")
    public BaseRespVo getPermissions(Integer roleId) {
        Map<String, Object> result = roleService.getPermissions(roleId);
        return new BaseRespVo<>(0, result, "成功");
    }

    @PostMapping("permissions")
    @RequiresPermissions("admin:role:permissions")
    public BaseRespVo updatePermissionsByRoleId(@RequestBody UpdatePermissionBean permissionBean) {

        if (permissionBean.getRoleId() == 1) return new BaseRespVo(641, null, "当前角色的超级权限不能更改");
        Integer result = roleService.updatePermissionsByRoleId(permissionBean);
        return new BaseRespVo(0, null, "成功");
    }
    //@Autowired
    //PermissionMapper permissionMapper;
    //
    //@PostMapping("insertpermission")
    //public void insertpermission(@RequestBody ArrayList<Permission> list) {
    //    System.out.println(list.size());
    //    insertpermissions(list, 0);
    //}
    //
    //private void insertpermissions(ArrayList<Permission> list, int pid) {
    //    for (Permission permission : list) {
    //        permission.setPid(pid);
    //        permissionMapper.insertSelective(permission);
    //        List<Permission> children = permission.getChildren();
    //        if (children!=null&&!children.isEmpty()) insertpermissions((ArrayList<Permission>) children, permission.getId());
    //    }
    //}
}
