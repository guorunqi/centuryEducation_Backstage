package com.example.demo.service;

import com.example.demo.dao.ProjectOrgMapper;
import com.example.demo.dao.SelfEvaluationEntryResultMapper;
import com.example.demo.domain.SelfEvaluationEntryResult;
import com.example.demo.domain.SelfEvaluationEntryResultExample;
import com.example.demo.util.CommonUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by guorunqi on 2019/2/23.
 */
@Service
public class SelfEvaluationEntryResultService {
    @Resource
    private SelfEvaluationEntryResultMapper selfEvaluationEntryResultMapper;
    public SelfEvaluationEntryResult saveSelfEvaluationEntryResult(SelfEvaluationEntryResult selfEvaluationEntryResult){
        try {
            if(StringUtils.isBlank(selfEvaluationEntryResult.getId())){
                selfEvaluationEntryResult.setId(CommonUtil.getPrimaryKey());
                selfEvaluationEntryResultMapper.insert(selfEvaluationEntryResult);
            }else {
                selfEvaluationEntryResultMapper.updateByPrimaryKeySelective(selfEvaluationEntryResult);
            }
            return selfEvaluationEntryResult;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public SelfEvaluationEntryResult querySelfEvaluationEntryResultByOrgID(String selfEvaluationEntryId,String projectOrgId){
        if(!StringUtils.isBlank(selfEvaluationEntryId)&&!StringUtils.isBlank(projectOrgId)){
            SelfEvaluationEntryResultExample selfEvaluationEntryResultExample=new SelfEvaluationEntryResultExample();
            selfEvaluationEntryResultExample.or().andSelfEvaluationEntryIdEqualTo(selfEvaluationEntryId)
                    .andProjectOrgIdEqualTo(projectOrgId);
            List<SelfEvaluationEntryResult> list=selfEvaluationEntryResultMapper.selectByExampleWithBLOBs(selfEvaluationEntryResultExample);
            if(list.size()>0){
                return list.get(0);
            }else{
                return null;
            }
        }else{
            return null;
        }
    }
}
