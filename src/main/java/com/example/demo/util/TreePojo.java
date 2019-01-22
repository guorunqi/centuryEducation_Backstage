package com.example.demo.util;

import java.util.List;

/**
 * Created by guorunqi on 2019/1/22.
 */
public class TreePojo {
    public String id;
    public String Pid;
    public String name;
    public List<TreePojo> childList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return Pid;
    }

    public void setPid(String pid) {
        Pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TreePojo> getChildList() {
        return childList;
    }

    public void setChildList(List<TreePojo> childList) {
        this.childList = childList;
    }
}
