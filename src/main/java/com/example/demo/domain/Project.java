package com.example.demo.domain;

import java.util.Date;

public class Project {
    private String id;

    private String name;

    private String classOne;

    private String classTwo;

    private Date startTime;

    private Date createTime;

    private Date endTime;

    private String remarks;

    private String stutas;

    private String updateNema;

    private String updateId;

    private Date updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getClassOne() {
        return classOne;
    }

    public void setClassOne(String classOne) {
        this.classOne = classOne == null ? null : classOne.trim();
    }

    public String getClassTwo() {
        return classTwo;
    }

    public void setClassTwo(String classTwo) {
        this.classTwo = classTwo == null ? null : classTwo.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getStutas() {
        return stutas;
    }

    public void setStutas(String stutas) {
        this.stutas = stutas == null ? null : stutas.trim();
    }

    public String getUpdateNema() {
        return updateNema;
    }

    public void setUpdateNema(String updateNema) {
        this.updateNema = updateNema == null ? null : updateNema.trim();
    }

    public String getUpdateId() {
        return updateId;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId == null ? null : updateId.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}