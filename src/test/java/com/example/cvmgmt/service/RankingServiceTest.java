package com.example.cvmgmt.service;

import com.example.cvmgmt.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RankingServiceTest {

    private RankingService rankingService;
    private Candidate candidate;
    private Vacancy vacancy;

    @BeforeEach
    void setUp() {
        rankingService = new RankingService();

        candidate = new Candidate();
        candidate.setId("1");
        candidate.setName("Siti Rahayu");
        candidate.setEmail("siti@example.com");
        candidate.setBirthdate(LocalDate.of(1996, 5, 15));
        candidate.setGender("FEMALE");
        candidate.setCurrentSalary(new BigDecimal("5500000"));

        Criteria ageCriteria = new Criteria();
        ageCriteria.setType(CriteriaType.AGE);
        ageCriteria.setDetails(Map.of("min", 22, "max", 30));
        ageCriteria.setWeight(3);

        Criteria genderCriteria = new Criteria();
        genderCriteria.setType(CriteriaType.GENDER);
        genderCriteria.setDetails(Map.of("value", "ANY"));
        genderCriteria.setWeight(1);

        Criteria salaryCriteria = new Criteria();
        salaryCriteria.setType(CriteriaType.SALARY_RANGE);
        salaryCriteria.setDetails(Map.of("min", "4500000", "max", "6500000"));
        salaryCriteria.setWeight(5);

        vacancy = new Vacancy();
        vacancy.setName("Junior Software Engineer");
        vacancy.setCriteriaList(List.of(ageCriteria, genderCriteria, salaryCriteria));
    }

    @Test
    void testCalculateScore() {
        int score = rankingService.calculateScore(candidate, vacancy);
        assertEquals(9, score);
    }

    @Test
    void testRankCandidates() {
        List<RankedCandidate> result = rankingService.rankCandidates(List.of(candidate), vacancy);
        assertEquals(1, result.size());
        assertEquals("siti@example.com", result.get(0).getEmail());
        assertEquals(9, result.get(0).getScore());
    }
}
