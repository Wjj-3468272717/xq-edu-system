package com.v1.controller;

import com.v1.pojo.Coach;
import com.v1.pojo.Member;
import com.v1.service.CoachService;
import com.v1.service.MemberService;
import com.v1.service.StatisticsService;
import com.v1.service.SubjectService;
import com.v1.utils.DataResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("statistics")
@RestController
public class StatisticsController {

    @Autowired
    StatisticsService statisticsService;
    @Autowired
    CoachService coachService;
    @Autowired
    MemberService memberService;
    @Autowired
    SubjectService subjectService;

    @GetMapping("tongji")
    public int[] tongji(Integer year){
        String[] array = {year + "-01",year + "-02",year + "-03",year + "-04",year + "-05",year + "-06",year + "-07",year + "-08",year + "-09",year + "-10",year + "-11",year + "-12"};
        int[] intar = new int[12];
        for(int i = 0; i < 12; i++){
            intar[i] = statisticsService.sumMoneryByDate(array[i]);
        }
        return intar;
    }

    @GetMapping("coachKpi")
    public Map<String,Object> coachKpi(String year){
        Map<String,Object> resultMap = new HashMap<>();
        List<Coach> list = coachService.list();
        for(Coach coach : list){
            int kpi = statisticsService.sumKpiByCoach(year,coach.getCoachId());
            resultMap.put(coach.getCoachName(),kpi);
        }
        return resultMap;
    }

    @GetMapping("counts")
    public Map<String,Integer> counts(){
        Map<String,Integer> resultMap = new HashMap<>();
        resultMap.put("memberCount",memberService.count());
        resultMap.put("coachCount",coachService.count());
        resultMap.put("subjectCount",subjectService.count());
        return resultMap;
    }

}
