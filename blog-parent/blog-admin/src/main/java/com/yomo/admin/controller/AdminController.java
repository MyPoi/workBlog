package com.yomo.admin.controller;

import com.yomo.admin.model.params.PageParam;
import com.yomo.admin.pojo.Permission;
import com.yomo.admin.service.PermissionService;
import com.yomo.admin.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 * TODO:请用一句话描述
 * </p>
 *
 * @author yomo
 * @since 2022/4/23
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    
    @Autowired
    private PermissionService permissionService;
    
    @PostMapping("/permission/permissionList")
    public Result listPermission(@RequestBody PageParam pageParam){
        return permissionService.listPermission(pageParam);
    }

    @PostMapping("permission/add")
    public Result add(@RequestBody Permission permission){
        return permissionService.add(permission);
    }

    @PostMapping("permission/update")
    public Result update(@RequestBody Permission permission){
        return permissionService.update(permission);
    }

    @GetMapping("permission/delete/{id}")
    public Result delete(@PathVariable("id") Long id){
        return permissionService.delete(id);
    }
    
}
