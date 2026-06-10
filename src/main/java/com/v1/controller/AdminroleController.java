package com.v1.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.v1.pojo.AdminUserRole;
import com.v1.pojo.Adminrole;
import com.v1.service.AdminUserRoleService;
import com.v1.service.AdminroleService;
import com.v1.utils.DataResults;
import com.v1.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author v1
 * @since 2026-06-10
 */
@RestController
@RequestMapping("/adminrole")
public class AdminroleController {

    @Autowired
    AdminroleService adminroleService;
    @Autowired
    AdminUserRoleService adminUserRoleService;

    @GetMapping("/list")
    public DataResults list(){
    List<Adminrole> list = adminroleService.list(new QueryWrapper<Adminrole>().eq("del",0));
        return DataResults.success(ResultCode.SUCCESS,list);
    }

    @GetMapping("queryPage")
    public Map<String,Object> queryPage(Integer pageSize, Integer pageNumber, String roleName){
        Map<String,Object> resultMap = new HashMap<>();
        QueryWrapper<Adminrole> q = new QueryWrapper<Adminrole>();
        q.like(StringUtils.isNotEmpty(roleName),"roleName",roleName);
        q.eq("del",0);
        IPage<Adminrole> page = adminroleService.page(new Page<Adminrole>(pageNumber, pageSize), q);
        resultMap.put("total",page.getTotal());
        resultMap.put("rows",page.getRecords());
        return resultMap;
    }

    @PostMapping("add")
    public DataResults add(Adminrole adminrole, String menusIds){
        try {
            adminrole.setDel("0");
            adminroleService.addRole(adminrole,menusIds);
            return DataResults.success(ResultCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return DataResults.success(ResultCode.FAIL);
        }
    }

    @GetMapping("queryById/{id}")
    public DataResults queryById(@PathVariable("id") Integer id){
        Adminrole adminrole = adminroleService.getById(id);
        return DataResults.success(ResultCode.SUCCESS,adminrole);
    }

    @PutMapping("update")
    public DataResults update(Adminrole adminrole, String menusIds){
        try {
            //更新角色
            adminroleService.updateRole(adminrole,menusIds);
            return DataResults.success(ResultCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return DataResults.success(ResultCode.FAIL);
        }
    }

    @DeleteMapping("delete/{id}")
    public DataResults delete(@PathVariable("id") Integer id){
        //判断是否存在需要删除的角色信息
        int count = adminUserRoleService.count(new QueryWrapper<AdminUserRole>().eq("roleId", id));
        if(count > 0){//当前角色和用户存在关联关系， 不能删除
            return DataResults.success(ResultCode.NO_DELETE);
        }else{
            //进行逻辑删除
            Adminrole adminrole = new Adminrole(id,"1");
            boolean result = adminroleService.delRole(adminrole);
            if(result){
                return DataResults.success(ResultCode.SUCCESS);
            }else{
                return DataResults.success(ResultCode.FAIL);
            }
        }
    }

}
