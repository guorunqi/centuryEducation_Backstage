package com.example.demo.service;

import com.example.demo.dao.QuotaSelfEvaluationEntryMapper;
import com.example.demo.domain.QuotaSelfEvaluationEntryKey;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * Created by guorunqi on 2019/3/8.
 */
@Service
public class QuotaSelfEvaluationEntryService {
    @Resource
    private QuotaSelfEvaluationEntryMapper quotaSelfEvaluationEntryMapper;
    public Boolean insertQuotaSelfEvaluationEntrys(QuotaSelfEvaluationEntryKey quotaSelfEvaluationEntryKey){
        try {
            quotaSelfEvaluationEntryMapper.insert(quotaSelfEvaluationEntryKey);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public Boolean deleteQuotaSelfEvaluationEntry(List<QuotaSelfEvaluationEntryKey> list){
        try {
            for(QuotaSelfEvaluationEntryKey quotaSelfEvaluationEntryKey:list){
                quotaSelfEvaluationEntryMapper.deleteByPrimaryKey(quotaSelfEvaluationEntryKey);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public List<HashMap> queryQuotaSelfEvaluationEntryByQuotaID(String id){
        return quotaSelfEvaluationEntryMapper.queryQuotaSelfEvaluationEntryByQuotaID(id);
    }
}
