package com.v1.service;

public interface StatisticsService {
    int sumMoneryByDate(String date);

    int sumKpiByCoach(String year, Integer coachId);
}
