package com.v1.controller;


import com.v1.pojo.Member;
import com.v1.pojo.Membertype;
import com.v1.service.MemberService;
import com.v1.service.MembertypeService;
import com.v1.utils.DataResults;
import com.v1.utils.DateTimeUtils;
import com.v1.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author v1
 * @since 2026-06-03
 */
@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    MemberService memberService;
    @Autowired
    MembertypeService membertypeService;

    /**
     * 分页查询member
     * @param hyname
     * @param ktype
     * @param pageSize
     * @param pageNumber
     * @return
     */
    @GetMapping("queryPage")
    public Map<String,Object> queryPage(String hyname, Integer ktype, Integer pageSize, Integer pageNumber){
        Map<String,Object> resMap = new HashMap<>();
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("hyname",hyname);
        paramMap.put("ktype",ktype);
        if(pageSize == null){
            pageSize = 10;
        }
        if(pageNumber == null){
            pageNumber = 1;
        }
        paramMap.put("pageStart",(pageNumber - 1) * pageSize);
        paramMap.put("pageSize",pageSize);

        int totalCount = memberService.totalCount(paramMap);
        List<Member> list = memberService.pageMembers(paramMap);
        resMap.put("total",totalCount);
        resMap.put("rows",list);
        return resMap;
    }

    /**
     * 新增member
     * @param member
     * @return
     */
    @PostMapping("/add")
    public DataResults add(Member member){
        member.setMemberbalance(0.0F);
        member.setMemberStatic(1);
        member.setDel(0);
        boolean saved = memberService.save(member);
        if(saved){
            return DataResults.success(ResultCode.SUCCESS);
        }else{
            return DataResults.success(ResultCode.FAIL);
        }
    }

    /**
     * 更新member
     * @param member
     * @return
     */
    @PutMapping("update")
    public DataResults update(Member member){
        member.setDel(0);
        boolean updated = memberService.updateById(member);
        if(updated){
            return DataResults.success(ResultCode.SUCCESS);
        }else{
            return DataResults.success(ResultCode.FAIL);
        }
    }

    /**
     * 根据id查询member
     * @param memberId
     * @return
     */
    @GetMapping("queryById/{memberId}")
    public DataResults queryById(@PathVariable("memberId") Integer memberId){
        Member member = memberService.getById(memberId);
        Integer types = member.getMemberTypes();
        Membertype membertype = membertypeService.getById(types);
        member.setMembertype(membertype);
        return DataResults.success(ResultCode.SUCCESS,member);
    }

    @GetMapping("queryPageExpire")
    public Map<String,Object> queryPageExpire(String hyname, Integer ktype, Integer pageSize, Integer pageNumber){
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> paramMap = new HashMap<>();

        paramMap.put("hyname",hyname);
        paramMap.put("ktype",ktype);
        if(pageSize == null){
            pageSize = 10;
        }
        if(pageNumber == null){
            pageNumber = 1;
        }
        paramMap.put("pageStart",(pageNumber - 1) * pageSize);
        paramMap.put("pageSize",pageSize);
        paramMap.put("nowTime", DateTimeUtils.nowTime());

        int totalCount = memberService.totalCount(paramMap);
        List<Member> list = memberService.pageMembers(paramMap);
        resultMap.put("total",totalCount);
        resultMap.put("rows",list);
        return resultMap;
    }


}
