package com.example.cvmgmt.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "vacancies")
public class Vacancy {
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCriteriaList(List<Criteria> criteriaList) {
        this.criteriaList = criteriaList;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Criteria> getCriteriaList() {
        return criteriaList;
    }

    @Id
    private String id;
    private String name;
    private List<Criteria> criteriaList;
    // Getters and setters
}
