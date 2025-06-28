package com.example.cvmgmt.controller;

import com.example.cvmgmt.model.*;
import com.example.cvmgmt.repository.CandidateRepository;
import com.example.cvmgmt.repository.VacancyRepository;
import com.example.cvmgmt.service.RankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ranking")
public class RankingController {

    @Autowired
    private CandidateRepository candidateRepo;

    @Autowired
    private VacancyRepository vacancyRepo;

    @Autowired
    private RankingService rankingService;

    @GetMapping("/{vacancyId}")
    public List<RankedCandidate> rankCandidates(@PathVariable String vacancyId) {
        Vacancy vacancy = vacancyRepo.findById(vacancyId).orElseThrow();
        List<Candidate> candidates = candidateRepo.findAll();
        return rankingService.rankCandidates(candidates, vacancy);
    }
}
