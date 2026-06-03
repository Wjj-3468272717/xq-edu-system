package com.v1.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.v1.pojo.Subject;
import com.v1.service.SubjectService;
import com.v1.utils.DataResults;
import com.v1.utils.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author v1
 * @since 2026-06-03
 */
@RestController
@RequestMapping("/subject")
@Slf4j
public class SubjectController {

    @Autowired
    SubjectService subjectService;

    @RequestMapping("/list")
    public DataResults list() {
        List<Subject> list = subjectService.list(new QueryWrapper<Subject>().eq("del", 0));
        return DataResults.success(ResultCode.SUCCESS, list);
    }

    @GetMapping("/queryPage")
    public Map<String, Object> queryPage(String subname, Integer pageSize, Integer pageNumber) {
        Map<String, Object> res = new HashMap<>();
        QueryWrapper<Subject> queryWrapper = new QueryWrapper<Subject>().like(subname != null && !subname.equals(""), "subname", subname);
        queryWrapper.eq("del",0);
        IPage<Subject> page = subjectService.page(new Page<Subject>(pageNumber, pageSize), queryWrapper);
        res.put("total",page.getTotal());
        res.put("rows",page.getRecords());
        return res;
    }

    @GetMapping("/subnameExist")
    public DataResults subNameExist(String subname){
        //select count(*) from subject where subname = ? and del = ?
        int count = subjectService.count(new QueryWrapper<Subject>().eq("subname", subname).eq("del", 0));
        return DataResults.success(ResultCode.SUCCESS,count);
    }

    @PostMapping("/add")
    public DataResults add(Subject subject){
        log.info("要新增的数据是："+subject);
        subject.setDel(0);
        boolean saved = subjectService.save(subject);
        if(saved){
            return DataResults.success(ResultCode.SUCCESS);
        }else{
            return DataResults.success(ResultCode.FAIL);
        }
    }

    @DeleteMapping("/delete/{id}")
    public DataResults delete(@PathVariable("id") Integer id){
        Subject subject = new Subject(id,1);
        boolean updated = subjectService.updateById(subject);
        if(updated){
            return DataResults.success(ResultCode.SUCCESS);
        }else{
            return DataResults.success(ResultCode.FAIL);
        }
    }

    @GetMapping("/queryById/{id}")
    public DataResults queryById(@PathVariable("id") Integer id){
        Subject subject = subjectService.getById(id);
        if(subject != null){
            return DataResults.success(ResultCode.SUCCESS,subject);
        }else{
            return DataResults.success(ResultCode.FAIL,null);
        }
    }

    @PutMapping("/update")
    public DataResults update(Subject subject){
        boolean updated = subjectService.updateById(subject);
        if(updated){
            return DataResults.success(ResultCode.SUCCESS);
        }else{
            return DataResults.fail(ResultCode.FAIL);
        }
    }

}
