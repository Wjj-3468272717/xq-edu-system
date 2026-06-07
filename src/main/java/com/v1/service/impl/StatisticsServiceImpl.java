package com.v1.service.impl;

import com.v1.mapper.StatisticsMapper;
import com.v1.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    StatisticsMapper statisticsMapper;
    @Autowired
    private StatisticsService statisticsService;

    @Override
    public int sumMoneryByDate(String date) {
        return statisticsMapper.sumMoneyByDate(date);
    }

    @Override
    public int sumKpiByCoach(String year, Integer coachId) {
        return statisticsMapper.sumKpiByCoach(year,coachId);
    }
}
