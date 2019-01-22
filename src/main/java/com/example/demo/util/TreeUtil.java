package com.example.demo.util;

import java.util.List;
import java.util.Map;

/**
 * Created by guorunqi on 2019/1/22.
 */
public class TreeUtil {
    public TreePojo getTree(List<E> datas,String idName,String pidName,String nameName){
        List<TreePojo> rootNodes;
        for(Object data:datas){
            System.out.println(((Map)data).get(idName));
        }
    }
}
