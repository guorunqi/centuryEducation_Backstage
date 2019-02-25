package com.example.demo.service;

import com.example.demo.dao.SelfEvaluationEntryMapper;
import com.example.demo.domain.SelfEvaluationEntry;
import com.example.demo.domain.SelfEvaluationEntryExample;
import com.example.demo.util.CommonUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by guorunqi on 2019/2/20.
 */
@Service
public class SelfEvaluationEntryService {
    @Resource
    private SelfEvaluationEntryMapper selfEvaluationEntryMapper;
    public List<SelfEvaluationEntry> queryAllSelfEvaluationEntry(){
        SelfEvaluationEntryExample selfEvaluationEntry=new SelfEvaluationEntryExample();
        return selfEvaluationEntryMapper.selectByExample(selfEvaluationEntry);
    }
    public SelfEvaluationEntry querySelfEvaluationEntryById(String id){
        return selfEvaluationEntryMapper.selectByPrimaryKey(id);
    }
    public Boolean deleteSelfEvaluationEntrys(List<SelfEvaluationEntry> list){
        try {
            if(list.size()>0) {
                for (SelfEvaluationEntry selfEvaluationEntry : list) {
                    if (selfEvaluationEntry.getId() != null && !selfEvaluationEntry.getId().equals("")) {
                        selfEvaluationEntryMapper.deleteByPrimaryKey(selfEvaluationEntry.getId());
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
    public Boolean saveSelfEvaluation(SelfEvaluationEntry selfEvaluationEntry){
        try {
            if(StringUtils.isBlank(selfEvaluationEntry.getId())){
                selfEvaluationEntry.setId(CommonUtil.getPrimaryKey());
                selfEvaluationEntryMapper.insert(selfEvaluationEntry);
            }else {
                selfEvaluationEntryMapper.updateByPrimaryKeySelective(selfEvaluationEntry);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }
}
