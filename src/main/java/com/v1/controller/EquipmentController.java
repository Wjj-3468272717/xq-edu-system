package com.v1.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.v1.pojo.Equipment;
import com.v1.pojo.Membertype;
import com.v1.service.EquipmentService;
import com.v1.utils.DataResults;
import com.v1.utils.ResultCode;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("/equipment")
@Slf4j
public class EquipmentController {

    @Autowired
    EquipmentService equipmentService;

    @GetMapping("/list")
    public DataResults list() {
        List<Equipment> list = equipmentService.list(new QueryWrapper<Equipment>().eq("del", 0));
        return DataResults.success(ResultCode.SUCCESS, list);
    }

    /**
     * 分页查询教学器材
     * @param eqName
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @GetMapping("/queryPage")
    public Map<String, Object> queryPage(String eqName, Integer pageNumber, Integer pageSize) {
        Map<String, Object> res = new HashMap<>();
        QueryWrapper<Equipment> q = new QueryWrapper<>();
        q.like(eqName != null && !eqName.equals(""), "eqName", eqName);
        q.eq("del", 0);//逻辑删除的数据不查询
        IPage<Equipment> page = equipmentService.page(new Page<Equipment>(pageNumber, pageSize), q);
        res.put("total", page.getTotal());
        res.put("rows", page.getRecords());
        return res;
    }

    /**
     * 新增教学器材
     * @param equipment
     * @return
     */
    @PostMapping("/add")
    public DataResults add(Equipment equipment) {
        equipment.setDel(0);
        boolean saved = equipmentService.save(equipment);
        if (saved) {
            return DataResults.success(ResultCode.SUCCESS);
        } else {
            return DataResults.success(ResultCode.FAIL);
        }
    }

    /**
     * 删除教学器材信息
     * @param eqId
     * @return
     */
    @DeleteMapping("/delete/{eqId}")
    public DataResults delete(@PathVariable("eqId") Integer eqId) {
        Equipment equipment = new Equipment(eqId, 1);
        boolean updated = equipmentService.updateById(equipment);
        if(updated){
            return DataResults.success(ResultCode.SUCCESS);
        } else {
            return DataResults.success(ResultCode.FAIL);
        }
    }

    /**
     * 回显教学器材信息
     * @param typeId
     * @return
     */
    @RequestMapping("/queryById/{typeId}")
    @GetMapping
    public DataResults queryById(@PathVariable("typeId") Integer typeId){
        Equipment equipment = equipmentService.getById(typeId);
        if(equipment != null){
            return DataResults.success(ResultCode.SUCCESS,equipment);
        }else{
            return DataResults.success(ResultCode.FAIL,null);
        }
    }

    /**
     * 修改教学器材类型数据
     * @param equipment
     * @return
     */
    @RequestMapping("/update")
    @PutMapping
    public DataResults update(Equipment equipment){
        log.info("更新之后的数据是："+equipment);
        boolean update = equipmentService.updateById(equipment);
        if(update){
            return DataResults.success(ResultCode.SUCCESS);
        }else{
            return DataResults.fail(ResultCode.FAIL);
        }
    }
}
