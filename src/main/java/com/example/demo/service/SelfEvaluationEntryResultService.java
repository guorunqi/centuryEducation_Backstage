package com.example.demo.service;

import com.example.demo.dao.SelfEvaluationEntryResultMapper;
import com.example.demo.domain.SelfEvaluationEntryResult;
import com.example.demo.util.CommonUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by guorunqi on 2019/2/23.
 */
@Service
public class SelfEvaluationEntryResultService {
    @Resource
    private SelfEvaluationEntryResultMapper selfEvaluationEntryResultMapper;
    public Boolean saveSelfEvaluationEntryResult(SelfEvaluationEntryResult selfEvaluationEntryResult){
        try {
            if(StringUtils.isBlank(selfEvaluationEntryResult.getId())){
                selfEvaluationEntryResult.setId(CommonUtil.getPrimaryKey());
                selfEvaluationEntryResultMapper.insert(selfEvaluationEntryResult);
            }else {
                selfEvaluationEntryResultMapper.updateByPrimaryKeySelective(selfEvaluationEntryResult);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
