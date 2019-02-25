package com.example.demo.domain;

public class SelfEvaluationEntryResult {
    private String id;

    private String selfEvaluationEntryId;

    private String projectOrgId;

    private String resultContent;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSelfEvaluationEntryId() {
        return selfEvaluationEntryId;
    }

    public void setSelfEvaluationEntryId(String selfEvaluationEntryId) {
        this.selfEvaluationEntryId = selfEvaluationEntryId == null ? null : selfEvaluationEntryId.trim();
    }

    public String getProjectOrgId() {
        return projectOrgId;
    }

    public void setProjectOrgId(String projectOrgId) {
        this.projectOrgId = projectOrgId == null ? null : projectOrgId.trim();
    }

    public String getResultContent() {
        return resultContent;
    }

    public void setResultContent(String resultContent) {
        this.resultContent = resultContent == null ? null : resultContent.trim();
    }
}