package com.example.demo.domain;

public class answerResult {
    private String id;

    private String answerId;

    private String projectOrgId;

    private Float selectionRate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId == null ? null : answerId.trim();
    }

    public String getProjectOrgId() {
        return projectOrgId;
    }

    public void setProjectOrgId(String projectOrgId) {
        this.projectOrgId = projectOrgId == null ? null : projectOrgId.trim();
    }

    public Float getSelectionRate() {
        return selectionRate;
    }

    public void setSelectionRate(Float selectionRate) {
        this.selectionRate = selectionRate;
    }
}