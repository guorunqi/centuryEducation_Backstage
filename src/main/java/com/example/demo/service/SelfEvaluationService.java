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
        SelfEvaluationExample selectByExample=new SelfEvaluationExample();
        SelfEvaluationExample.Criteria criteria=selectByExample.createCriteria();
        if(!StringUtils.isBlank(map.get("name").toString())){
            criteria.andNameLike(map.get("name").toString());
        }
        if(!StringUtils.isBlank(map.get("projectName").toString())){
            criteria.andNameLike(map.get("projectName").toString());
        }
        return selfEvaluationMapper.selectByExample(selectByExample);
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
    public Boolean saveSelfEvaluation(SelfEvaluation selfEvaluation){
        try {
            if(StringUtils.isBlank(selfEvaluation.getId())){
                selfEvaluation.setId(CommonUtil.getPrimaryKey());
                selfEvaluationMapper.insert(selfEvaluation);
            }else {
                selfEvaluationMapper.updateByPrimaryKeySelective(selfEvaluation);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }
}
