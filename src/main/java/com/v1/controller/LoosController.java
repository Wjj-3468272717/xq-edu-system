package com.v1.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.v1.pojo.Loos;
import com.v1.service.LoosService;
import com.v1.utils.DataResults;
import com.v1.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author v1
 * @since 2026-06-07
 */
@RestController
@RequestMapping("/loos")
public class LoosController {

    @Autowired
    LoosService loosService;

    @GetMapping("queryPage")
    public Map<String,Object> queryPage(Integer pageSize, Integer pageNumber,String loosName){
        Map<String,Object> resultMap = new HashMap<>();
        QueryWrapper<Loos> q = new QueryWrapper<>();
        q.eq("del",0);
        q.like(StringUtils.isNotEmpty(loosName),"loosName",loosName);
        IPage<Loos> page = loosService.page(new Page<Loos>(pageNumber, pageSize), q);
        resultMap.put("total",page.getTotal());
        resultMap.put("rows",page.getRecords());
        return resultMap;
    }

    @PostMapping("add")
    public DataResults add(Loos loos){
        loos.setDel(0);
        //TODO: 后期获取登录用户
        loos.setAdmin("匿名");
        boolean saved = loosService.save(loos);
        if(saved){
            return DataResults.success(ResultCode.SUCCESS);
        }else{
            return DataResults.success(ResultCode.FAIL);
        }
    }

    @GetMapping("queryById/{id}")
    public DataResults queryById(@PathVariable("id") Integer id){
        Loos loos = loosService.getById(id);
        return DataResults.success(ResultCode.SUCCESS,loos);
    }

    @PutMapping("update")
    public DataResults update(Loos loos){
        loos.setDel(0);
        //TODO: 后期获取登录用户
        loos.setAdmin("匿名");
        boolean update = loosService.updateById(loos);
        if(update){
            return DataResults.success(ResultCode.SUCCESS);
        }else{
            return DataResults.success(ResultCode.FAIL);
        }
    }

}
