package com.example.demo.util;

import java.util.List;

/**
 * Created by guorunqi on 2019/1/22.
 */
public class TreeTablePojo {
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

    public String getExped1() {
        return exped1;
    }

    public void setExped1(String exped1) {
        this.exped1 = exped1;
    }

    public String getExped2() {
        return exped2;
    }

    public void setExped2(String exped2) {
        this.exped2 = exped2;
    }

    public String getExped3() {
        return exped3;
    }

    public void setExped3(String exped3) {
        this.exped3 = exped3;
    }

    public List<TreeTablePojo> getChildren() {
        return children;
    }

    public void setChildren(List<TreeTablePojo> children) {
        this.children = children;
    }

    public String id;
    public String Pid;
    public String name;
    public String exped1;
    public String exped2;
    public String exped3;
    public List<TreeTablePojo> children;

}
