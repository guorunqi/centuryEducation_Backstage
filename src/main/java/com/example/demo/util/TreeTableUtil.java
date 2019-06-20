package com.example.demo.util;

import com.example.demo.domain.Organization;
import com.example.demo.domain.PolicyDocumentEntry;
import com.example.demo.domain.Quota;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public List<TreeTablePojo> HashMapToTree(List<HashMap> list){
        List<TreeTablePojo> pojos=new ArrayList<TreeTablePojo>();
        for(HashMap map:list){
            TreeTablePojo tree =new TreeTablePojo();
            tree.setId(map.get("id").toString());
            tree.setPid(map.get("pId").toString());
            tree.setName(map.get("qrId").toString());
            tree.setExped1(map.get("weight").toString());
            tree.setExped2(map.get("score").toString());
            tree.setChildren(new ArrayList<TreeTablePojo>());
            pojos.add(tree);
        }
        return pojos;
    }
    public List<TreeTablePojo> sortTreeTablePojos(List<TreeTablePojo> list){
        if(list!=null){
            for(int i=0;i<list.size();i++){
                if(list.size()>1) {
                    for (int j = i + 1; j < list.size(); j++) {
                        TreeTablePojo p1 = list.get(i);
                        TreeTablePojo p2 = list.get(j);
                        int p1Num = Integer.valueOf(this.getNumbers(p1.getName()));
                        int p2Num = Integer.valueOf(this.getNumbers(p2.getName()));
                        if (p2Num < p1Num) {
                            list.add(i, p2);
                            list.add(j, p1);
                            list.remove(i + 1);
                            list.remove(j + 1);
                        }
                    }
                }
                list.get(i).setChildren(this.sortTreeTablePojos(list.get(i).getChildren()));
            }
        }
        return list;
    }
    //截取数字
    public String getNumbers(String content) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            return matcher.group(0);
        }
        return "0";
    }
}
