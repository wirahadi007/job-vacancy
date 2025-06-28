package com.example.cvmgmt.controller;

import com.example.cvmgmt.model.Candidate;
import com.example.cvmgmt.repository.CandidateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/candidates")
public class CandidateController {

    private static final Logger logger = LoggerFactory.getLogger(CandidateController.class);

    @Autowired
    private CandidateRepository repo;

    @PostMapping
    public Candidate create(@RequestBody Candidate c) {
        logger.info("Creating candidate: {}", c.getEmail());
        if (repo.findByEmail(c.getEmail()).isPresent()) {
            logger.warn("Duplicate email detected: {}", c.getEmail());
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
        }
        return repo.save(c);
    }

    @GetMapping
    public List<Candidate> getAll() {
        logger.info("Retrieving all candidates");
        return repo.findAll();
    }

    @PutMapping("/{id}")
    public Candidate update(@PathVariable String id, @RequestBody Candidate c) {
        Candidate existing = repo.findById(id).orElseThrow();
        c.setId(id);
        return repo.save(c);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        repo.deleteById(id);
    }
}
