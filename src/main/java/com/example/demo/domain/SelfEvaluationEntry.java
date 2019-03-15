package com.example.demo.domain;

public class SelfEvaluationEntry {
    private String id;

    private String title;

    private Integer sortno;

    private String selfEvaluationId;

    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getSortno() {
        return sortno;
    }

    public void setSortno(Integer sortno) {
        this.sortno = sortno;
    }

    public String getSelfEvaluationId() {
        return selfEvaluationId;
    }

    public void setSelfEvaluationId(String selfEvaluationId) {
        this.selfEvaluationId = selfEvaluationId == null ? null : selfEvaluationId.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}