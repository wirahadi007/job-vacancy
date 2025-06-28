package com.example.cvmgmt.model;

import java.util.Map;

public class Criteria {
    private CriteriaType type;
    private Map<String, Object> details;
    private int weight = 1;
    // Getters and setters

    public CriteriaType getType() {
        return type;
    }

    public Map<String, Object> getDetails() {
        return details;
    }

    public void setType(CriteriaType type) {
        this.type = type;
    }

    public void setDetails(Map<String, Object> details) {
        this.details = details;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }
}
