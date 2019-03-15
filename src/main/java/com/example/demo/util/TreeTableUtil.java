package com.example.demo.util;

import com.example.demo.domain.Organization;
import com.example.demo.domain.PolicyDocumentEntry;
import com.example.demo.domain.Quota;

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
    public List<TreeTablePojo> QuotaToTree(List<Quota> Quotas){
        List<TreeTablePojo> list=new ArrayList<TreeTablePojo>();
        for(Quota quota:Quotas){
            TreeTablePojo tree =new TreeTablePojo();
            tree.setId(quota.getId());
            tree.setPid(quota.getpId());
            tree.setName(quota.getCode());
            tree.setExped1(quota.getName());
            tree.setExped2(quota.getHierarchy());
            tree.setExped3(quota.getWeight().toString());
            tree.setChildren(new ArrayList<TreeTablePojo>());
            list.add(tree);
        }
        return list;
    }
    public TreeTablePojo getTreeTablePojo(List<TreeTablePojo> list,String id){
        for(TreeTablePojo treeTablePojo:list){
            if(treeTablePojo.getId().equals(id)){
                return treeTablePojo;
            }
            if(treeTablePojo.getChildren().size()>0){
                TreeTablePojo treeTablePojo1=getSonTreeTablePojo(treeTablePojo,id);
                if(treeTablePojo1!=null){
                    return treeTablePojo1;
                }
            }
        }
        return null;
    }
    public TreeTablePojo getSonTreeTablePojo(TreeTablePojo treeTablePojo,String id){
        for(TreeTablePojo table:treeTablePojo.getChildren()){
            if(table.getId().equals(id)){
                return table;
            }
            if(table.getChildren().size()>0){
                TreeTablePojo getSonTreeTablePojo1=getSonTreeTablePojo(table,id);
                if(getSonTreeTablePojo1!=null){
                    return getSonTreeTablePojo1;
                }
            }
        }
        return null;
    }
}
