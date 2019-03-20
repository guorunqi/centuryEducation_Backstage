package com.example.demo.domain;

public class QuotaResult {
    private String id;

    private String quotaId;

    private String projectOrgId;

    private String results;

    private Float score;

    private String expertId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getQuotaId() {
        return quotaId;
    }

    public void setQuotaId(String quotaId) {
        this.quotaId = quotaId == null ? null : quotaId.trim();
    }

    public String getProjectOrgId() {
        return projectOrgId;
    }

    public void setProjectOrgId(String projectOrgId) {
        this.projectOrgId = projectOrgId == null ? null : projectOrgId.trim();
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results == null ? null : results.trim();
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public String getExpertId() {
        return expertId;
    }

    public void setExpertId(String expertId) {
        this.expertId = expertId == null ? null : expertId.trim();
    }
}