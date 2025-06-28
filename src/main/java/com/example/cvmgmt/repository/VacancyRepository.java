package com.example.cvmgmt.repository;

import com.example.cvmgmt.model.Vacancy;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VacancyRepository extends MongoRepository<Vacancy, String> {
}
