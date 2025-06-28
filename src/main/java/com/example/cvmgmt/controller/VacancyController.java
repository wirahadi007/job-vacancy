package com.example.cvmgmt.controller;

import com.example.cvmgmt.model.Vacancy;
import com.example.cvmgmt.repository.VacancyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vacancies")
public class VacancyController {

    @Autowired
    private VacancyRepository repo;

    @PostMapping
    public Vacancy create(@RequestBody Vacancy v) {
        return repo.save(v);
    }

    @GetMapping
    public List<Vacancy> getAll() {
        return repo.findAll();
    }

    @PutMapping("/{id}")
    public Vacancy update(@PathVariable String id, @RequestBody Vacancy v) {
        v.setId(id);
        return repo.save(v);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        repo.deleteById(id);
    }
}
