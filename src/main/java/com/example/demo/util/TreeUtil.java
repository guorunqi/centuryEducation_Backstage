package com.example.demo.util;

import com.example.demo.domain.Organization;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by guorunqi on 2019/1/22.
 */
public class TreeUtil {
    public List<TreePojo> getTree(List<TreePojo> treePojos){
        List<TreePojo> rootNodes=new ArrayList<TreePojo>();
        for(TreePojo pojo:treePojos){
            if(pojo.getPid()==null||pojo.getPid().equals("")){
                rootNodes.add(pojo);
            }
        }
        for(TreePojo pojo:rootNodes){
            pojo=getSonTree(pojo,treePojos);
        }
        return rootNodes;
    }
    private TreePojo getSonTree(TreePojo pojo,List<TreePojo> treePojos){
        for(TreePojo sonPojo:treePojos){
            if(sonPojo.getPid()!=null&&sonPojo.getPid().equals(pojo.getId())){
                pojo.getChildList().add(sonPojo);
            }
        }
        if(pojo.getChildList().size()==0){
            return pojo;
        }else{
            for(TreePojo sonPojo:pojo.getChildList()){
                sonPojo=getSonTree(sonPojo,treePojos);
            }
        }
        return pojo;
    }
    public List<TreePojo> OrgToTree(List<Organization> orgs){
        List<TreePojo> list=new ArrayList<TreePojo>();
        for(Organization org:orgs){
            TreePojo tree=new TreePojo();
            tree.setId(org.getId());
            tree.setPid(org.getpId());
            tree.setName(org.getName());
            tree.setChildList(new ArrayList<TreePojo>());
            list.add(tree);
        }
        return list;
    }
}
