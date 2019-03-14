package com.example.demo.domain;

public class QuotaSelfEvaluationEntryKey {
    private String selfEvaluationEntryId;

    private String quotaId;

    public String getSelfEvaluationEntryId() {
        return selfEvaluationEntryId;
    }

    public void setSelfEvaluationEntryId(String selfEvaluationEntryId) {
        this.selfEvaluationEntryId = selfEvaluationEntryId == null ? null : selfEvaluationEntryId.trim();
    }

    public String getQuotaId() {
        return quotaId;
    }

    public void setQuotaId(String quotaId) {
        this.quotaId = quotaId == null ? null : quotaId.trim();
    }
}