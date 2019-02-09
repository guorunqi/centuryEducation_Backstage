package com.example.demo.util;

import com.example.demo.domain.Organization;
import com.example.demo.domain.PolicyDocumentEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weishouhao on 2019/1/22.
 */
public class TreeTableUtil {
    public List<TreeTablePojo> getTree(List<TreeTablePojo> treePojos){
        List<TreeTablePojo> rootNodes=new ArrayList<TreeTablePojo>();
        for(TreeTablePojo pojo:treePojos){
            if(pojo.getPid()==null||pojo.getPid().equals("")){
                rootNodes.add(pojo);
            }
        }
        for(TreeTablePojo pojo:rootNodes){
            pojo=getSonTree(pojo,treePojos);
        }
        return rootNodes;
    }
    private TreeTablePojo getSonTree(TreeTablePojo pojo,List<TreeTablePojo> treePojos){
        for(TreeTablePojo sonPojo:treePojos){
            if(sonPojo.getPid()!=null&&sonPojo.getPid().equals(pojo.getId())){
                pojo.getChildren().add(sonPojo);
            }
        }
        if(pojo.getChildren().size()==0){
            return pojo;
        }else{
            for(TreeTablePojo sonPojo:pojo.getChildren()){
                sonPojo=getSonTree(sonPojo,treePojos);
            }
        }
        return pojo;
    }
    public List<TreeTablePojo> PolicyDocumentEntryToTree(List<PolicyDocumentEntry> PolicyDocumentEntrys){
        List<TreeTablePojo> list=new ArrayList<TreeTablePojo>();
        for(PolicyDocumentEntry PolicyDocumentEntry:PolicyDocumentEntrys){
            TreeTablePojo tree =new TreeTablePojo();
            tree.setId(PolicyDocumentEntry.getId());
            tree.setPid(PolicyDocumentEntry.getParentId());
            tree.setName(PolicyDocumentEntry.getName());
            tree.setExped1(PolicyDocumentEntry.getContent());
            tree.setChildren(new ArrayList<TreeTablePojo>());
            list.add(tree);
        }
        return list;
    }
}
