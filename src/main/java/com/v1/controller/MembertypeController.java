package com.v1.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.v1.pojo.Membertype;
import com.v1.service.MembertypeService;
import com.v1.utils.DataResults;
import com.v1.utils.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author v1
 * @since 2026-06-02
 */
@Controller
@RequestMapping("/membertype")
@Slf4j
public class MembertypeController {
    @Autowired
    MembertypeService membertypeService;

    @RequestMapping("/testThymeleaf")
    public String testThymeleaf(Model model){
        model.addAttribute("userName","eric");
        return "hello";
    }

    @RequestMapping("/list")
    public @ResponseBody List<Membertype> list(){
        List<Membertype> list = membertypeService.list();
        return list;
    }

    @RequestMapping("queryPage")
    @ResponseBody
    public Map<String,Object> queryPage(String typeName, Integer pageNumber, Integer pageSize){
        Map<String,Object> resultMap = new HashMap<>();
        log.info("typeName:" + typeName);
        log.info("pageSize:" + pageSize);
        log.info("pageNumber:" + pageNumber);
        //select * from membertype where typeName like "%typeName%"
        QueryWrapper<Membertype> q = new QueryWrapper<>();
        q.like(typeName != null &&  !"".equals(typeName),"typeName",typeName);
        q.eq("typeDel",0);//查询没有逻辑删除的数据
        IPage<Membertype> iPage = membertypeService.page(new Page<Membertype>(pageNumber, pageSize), q);
        resultMap.put("total",iPage.getTotal());
        resultMap.put("rows", iPage.getRecords());
        return resultMap;
    }

    @RequestMapping("/add")
    @ResponseBody
    public DataResults add(Membertype membertype){
        log.info("新增数据是："+membertype);
        membertype.setTypeDel(0);
        boolean save = membertypeService.save(membertype);
        if(save){
            return DataResults.success(ResultCode.SUCCESS);
        }else{
            return DataResults.success(ResultCode.FAIL);
        }
    }

    /**
     * 根据会员卡id回显会员卡信息
     * @param typeId
     * @return
     */
    @RequestMapping("/queryById/{typeId}")
    @ResponseBody
    public DataResults queryById(@PathVariable("typeId") Integer typeId){
        Membertype membertype = membertypeService.getById(typeId);
        if(membertype != null){
            return DataResults.success(ResultCode.SUCCESS,membertype);
        }else{
            return DataResults.success(ResultCode.FAIL,null);
        }
    }

}
