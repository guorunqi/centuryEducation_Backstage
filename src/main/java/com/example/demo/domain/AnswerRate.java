package com.example.demo.domain;

public class AnswerRate {
    private String id;

    private String problemId;

    private String code;

    private String content;

    private Double selectionRate;

    public Double getSelectionRate() {
        return selectionRate;
    }

    public void setSelectionRate(Double selectionRate) {
        this.selectionRate = selectionRate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getProblemId() {
        return problemId;
    }

    public void setProblemId(String problemId) {
        this.problemId = problemId == null ? null : problemId.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}