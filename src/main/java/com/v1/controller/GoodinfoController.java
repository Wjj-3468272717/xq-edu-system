package com.v1.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.v1.pojo.Goodinfo;
import com.v1.service.GoodinfoService;
import com.v1.service.GoodsService;
import com.v1.service.MemberService;
import com.v1.utils.DataResults;
import com.v1.utils.DateTimeUtils;
import com.v1.utils.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author v1
 * @since 2026-06-06
 */
@Slf4j
@RestController
@RequestMapping("/goodinfo")
public class GoodinfoController {

    @Autowired
    GoodinfoService goodinfoService;
    @Autowired
    MemberService memberService;
    @Autowired
    GoodsService goodsService;

    @GetMapping("list")
    public DataResults list(){
        List<Goodinfo> list = goodinfoService.list(new QueryWrapper<Goodinfo>().eq("del",0));
        return DataResults.success(ResultCode.SUCCESS,list);
    }

    @GetMapping("queryPage")
    public Map<String,Object> queryPage(Integer pageSize, Integer pageNumber, Integer goodsid, Integer memberid){
        Map<String,Object> resultMap = new HashMap<>();
        QueryWrapper q = new QueryWrapper<>();
        q.eq(goodsid != null,"goodsid",goodsid);
        q.eq(memberid != null,"memberid",memberid);
        q.eq("del",0);
        IPage<Goodinfo> page = goodinfoService.page(new Page<Goodinfo>(pageNumber, pageSize), q);
        long pageTotal = page.getTotal();
        List<Goodinfo> records = page.getRecords();
        for(Goodinfo goodinfo : records){
            goodinfo.setMember(memberService.getById(goodinfo.getMemberid()));
            goodinfo.setGoods(goodsService.getById(goodinfo.getGoodsid()));
        }
        resultMap.put("total",pageTotal);
        resultMap.put("rows",records);
        return resultMap;
    }

    @PostMapping("add")
    public DataResults add(Goodinfo goodinfo){
        goodinfo.setDel(0);
        goodinfo.setCreatetime(DateTimeUtils.nowTime());
        Boolean saved = goodinfoService.sell(goodinfo);
        if(saved){
            return DataResults.success(ResultCode.SUCCESS);
        }else{
            return DataResults.success(ResultCode.FAIL);
        }
    }

//    @RolesAllowed(value = {"ROLE_管理员","ROLE_教师"})
//    @Secured(value = {"ROLE_管理员","ROLE_教师"})
    @PreAuthorize(value = "hasAnyRole('ROLE_管理员','ROLE_教师')")
    @DeleteMapping("delete/{id}")
    public DataResults delete(@PathVariable("id") Integer id){
        Goodinfo goodinfo = new Goodinfo(id,1);
        boolean updated = goodinfoService.updateById(goodinfo);
        if(updated){
            return DataResults.success(ResultCode.SUCCESS);
        }else{
            return DataResults.success(ResultCode.FAIL);
        }
    }

    @DeleteMapping("dellist")
    public DataResults dellist(String array){
        log.info("传递的数据是："+array);
        String[] ids = array.split(",");
        List<Goodinfo> list = goodinfoService.list();
        for(String id : ids){
            list.add(new Goodinfo(Integer.parseInt(id),1));
        }
        goodinfoService.updateBatchById(list);
        return DataResults.success(ResultCode.SUCCESS);
    }

}
