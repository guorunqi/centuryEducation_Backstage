package com.example.demo.service;

import com.example.demo.dao.SelfEvaluationMapper;
import com.example.demo.domain.SelfEvaluation;
import com.example.demo.domain.SelfEvaluationExample;
import com.example.demo.util.CommonUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * Created by guorunqi on 2019/2/16.
 */
@Service
public class SelfEvaluationService {
    @Resource
    SelfEvaluationMapper selfEvaluationMapper;
    public List<SelfEvaluation> querySelfEvaluation(HashMap map){
        return selfEvaluationMapper.querySelfEvaluation(map.get("name").toString(),map.get("projectName").toString());
    }
    public SelfEvaluation querySelfEvaluationById(String id){
        return selfEvaluationMapper.selectByPrimaryKey(id);
    }
    public Boolean deleteSelfEvaluations(List<SelfEvaluation> list){
        try {
            if(list.size()>0) {
                for (SelfEvaluation selfEvaluation : list) {
                    if (selfEvaluation.getId() != null && !selfEvaluation.getId().equals("")) {
                        selfEvaluationMapper.deleteByPrimaryKey(selfEvaluation.getId());
                    }else{
                        return false;
                    }
                }
                return true;
            }else{
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public String saveSelfEvaluation(SelfEvaluation selfEvaluation){
        try {
            if(StringUtils.isBlank(selfEvaluation.getId())){
                selfEvaluation.setId(CommonUtil.getPrimaryKey());
                selfEvaluationMapper.insert(selfEvaluation);
            }else {
                selfEvaluationMapper.updateByPrimaryKeySelective(selfEvaluation);
            }
            return selfEvaluation.getId();
        }catch (Exception e){
            e.printStackTrace();
            return "-1";
        }

    }
    public List<HashMap> querySelfEvaluationDataByID(String id){
        List<HashMap> list=selfEvaluationMapper.querySelfEvaluationDataByID(id);
        for(HashMap map:list){
            map.put("title",map.get("name").toString()+":"+map.get("title").toString());
        }
        return list;
    }
    public List<HashMap> queryPolicyDocumentEntryByID(String id){
        List<HashMap> list=selfEvaluationMapper.queryPolicyDocumentEntryByID(id);
        for(HashMap map:list){
            map.put("title",map.get("title").toString()+":"+map.get("name").toString());
        }
        return list;
    }
    public List<HashMap> queryProblemByID(String id){
        List<HashMap> list=selfEvaluationMapper.queryProblemByID(id);
        for(HashMap map:list){
            map.put("title",map.get("title").toString()+":"+map.get("name").toString());
        }
        return list;
    }

}
