package com.v1.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.v1.pojo.Coach;
import com.v1.service.CoachService;
import com.v1.utils.DataResults;
import com.v1.utils.ResultCode;
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
 * @since 2026-06-05
 */
@RestController
@RequestMapping("/coach")
public class CoachController {

    @Autowired
    CoachService coachService;

    @GetMapping("list")
    public DataResults list() {
        List<Coach> list = coachService.list(new QueryWrapper<Coach>().eq("del", 0));
        return DataResults.success(ResultCode.SUCCESS, list);
    }

    @GetMapping("queryPage")
    public Map<String, Object> queryPage(Integer pageSize, Integer pageNumber, String coachName) {
        Map<String, Object> resultMap = new HashMap<>();
        QueryWrapper<Coach> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(coachName), "coachName", coachName);
        queryWrapper.eq("del", 0);
        IPage<Coach> page = coachService.page(new Page<Coach>(pageNumber, pageSize), queryWrapper);
        resultMap.put("total", page.getTotal());
        resultMap.put("rows", page.getRecords());
        return resultMap;
    }

    @GetMapping("coachNameExist")
    public DataResults coachNameExist(String coachName) {
        //select count(*) from coach where coachNam = ? and del = 0
        int count = coachService.count(new QueryWrapper<Coach>().eq("coachName", coachName).eq("del", 0));
        return DataResults.success(ResultCode.SUCCESS, count);
    }

    @PostMapping("add")
    public DataResults add(Coach coach) {
        coach.setDel(0);
        boolean saved = coachService.save(coach);
        if (saved) {
            return DataResults.success(ResultCode.SUCCESS);
        } else {
            return DataResults.success(ResultCode.FAIL);
        }
    }

    @GetMapping("queryById/{id}")
    public DataResults queryById(@PathVariable("id") Integer id) {
        Coach coach = coachService.getById(id);
        if (coach != null) {
            return DataResults.success(ResultCode.SUCCESS, coach);
        } else {
            return DataResults.fail(ResultCode.FAIL);
        }
    }

    @PutMapping("update")
    public DataResults update(Coach coach) {
        boolean updated = coachService.updateById(coach);
        if (updated) {
            return DataResults.success(ResultCode.SUCCESS);
        } else {
            return DataResults.fail(ResultCode.FAIL);
        }
    }

    @DeleteMapping("delete/{id}")
    public DataResults delete(@PathVariable("id") Integer id) {
        Coach coach = coachService.getById(id);
        coach.setDel(1);
        boolean updated = coachService.updateById(coach);
        if (updated) {
            return DataResults.success(ResultCode.SUCCESS);
        } else {
            return DataResults.fail(ResultCode.FAIL);
        }
    }

    @PutMapping("availableList")
    public DataResults availableList() {
        List<Coach> coachList = coachService.list(new
                QueryWrapper<Coach>().eq("coachStatic", 0).eq("del", 0));
        return DataResults.success(ResultCode.SUCCESS, coachList);
    }

}
