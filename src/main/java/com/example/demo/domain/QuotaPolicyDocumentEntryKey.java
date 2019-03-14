package com.example.demo.domain;

public class QuotaPolicyDocumentEntryKey {
    private String policyDocumentEntryId;

    private String quotaId;

    public String getPolicyDocumentEntryId() {
        return policyDocumentEntryId;
    }

    public void setPolicyDocumentEntryId(String policyDocumentEntryId) {
        this.policyDocumentEntryId = policyDocumentEntryId == null ? null : policyDocumentEntryId.trim();
    }

    public String getQuotaId() {
        return quotaId;
    }

    public void setQuotaId(String quotaId) {
        this.quotaId = quotaId == null ? null : quotaId.trim();
    }
}