package com.example.demo.domain;

public class QuotaProblemKey {
    private String problemId;

    private String quotaId;

    public String getProblemId() {
        return problemId;
    }

    public void setProblemId(String problemId) {
        this.problemId = problemId == null ? null : problemId.trim();
    }

    public String getQuotaId() {
        return quotaId;
    }

    public void setQuotaId(String quotaId) {
        this.quotaId = quotaId == null ? null : quotaId.trim();
    }
}