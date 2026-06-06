package com.v1.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.v1.pojo.Goods;
import com.v1.service.GoodsService;
import com.v1.utils.DataResults;
import com.v1.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    GoodsService goodsService;

    @GetMapping("queryPage")
    public Map<String,Object> queryPage(Integer pageNumber, Integer pageSize, String goodsName){
        Map<String,Object> resultMap = new HashMap<>();
        QueryWrapper<Goods> q = new QueryWrapper<>();
        q.like(StringUtils.isNotEmpty(goodsName),"goodsName",goodsName);
        q.eq("del",0);
        IPage<Goods> page = goodsService.page(new Page<Goods>(pageNumber, pageSize), q);
        long pageTotal = page.getTotal();
        List<Goods> rows = page.getRecords();
        resultMap.put("total",pageTotal);
        resultMap.put("rows",rows);
        return resultMap;
    }

    @GetMapping("goodsNameExist")
    public DataResults goodsNameExist(String goodsName){
        int count = goodsService.count(new QueryWrapper<Goods>().eq("goodsName", goodsName).eq("del", 0));
        return DataResults.success(ResultCode.SUCCESS,count);
    }

    @PostMapping("add")
    public DataResults add(Goods goods){
        goods.setDel(0);
        boolean saved = goodsService.save(goods);
        if(saved){
            return DataResults.success(ResultCode.SUCCESS);
        }else{
            return DataResults.success(ResultCode.FAIL);
        }
    }

}
