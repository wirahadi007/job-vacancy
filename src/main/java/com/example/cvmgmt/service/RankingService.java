package com.example.cvmgmt.service;

import com.example.cvmgmt.model.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;
import java.util.List;

@Service
public class RankingService {
    public int calculateScore(Candidate candidate, Vacancy vacancy) {
        int score = 0;
        for (Criteria c : vacancy.getCriteriaList()) {
            switch (c.getType()) {
                case AGE -> {
                    int age = Period.between(candidate.getBirthdate(), LocalDate.now()).getYears();
                    int min = (int) c.getDetails().getOrDefault("min", 0);
                    int max = (int) c.getDetails().getOrDefault("max", 100);
                    if (age >= min && age <= max) score += c.getWeight();
                }
                case GENDER -> {
                    String requiredGender = (String) c.getDetails().get("value");
                    if ("ANY".equalsIgnoreCase(requiredGender) || requiredGender.equalsIgnoreCase(candidate.getGender())) {
                        score += c.getWeight();
                    }
                }
                case SALARY_RANGE -> {
                    BigDecimal min = new BigDecimal(c.getDetails().getOrDefault("min", "0").toString());
                    BigDecimal max = new BigDecimal(c.getDetails().getOrDefault("max", "999999999").toString());
                    if (candidate.getCurrentSalary().compareTo(min) >= 0 && candidate.getCurrentSalary().compareTo(max) <= 0) {
                        score += c.getWeight();
                    }
                }
            }
        }
        return score;
    }

    public List<RankedCandidate> rankCandidates(List<Candidate> candidates, Vacancy vacancy) {
        return candidates.stream()
                .map(c -> new RankedCandidate(c.getId(), c.getName(), c.getEmail(), calculateScore(c, vacancy)))
                .sorted(Comparator.comparingInt(RankedCandidate::getScore).reversed())
                .toList();
    }
}
