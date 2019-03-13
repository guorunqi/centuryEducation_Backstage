package com.example.demo.domain;

public class Problem {
    private String id;

    private String questionnaireId;

    private String content;

    private String exhibitionType;

    private String answerType;

    private String status;

    private String remarks;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(String questionnaireId) {
        this.questionnaireId = questionnaireId == null ? null : questionnaireId.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getExhibitionType() {
        return exhibitionType;
    }

    public void setExhibitionType(String exhibitionType) {
        this.exhibitionType = exhibitionType == null ? null : exhibitionType.trim();
    }

    public String getAnswerType() {
        return answerType;
    }

    public void setAnswerType(String answerType) {
        this.answerType = answerType == null ? null : answerType.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }
}