package com.example.cvmgmt.model;

public class RankedCandidate {
    private String id;
    private String name;
    private String email;
    private int score;

    public RankedCandidate(String id, String name, String email, int score) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getScore() {
        return score;
    }
// Getters and setters
}
