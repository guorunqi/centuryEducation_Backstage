package com.example.demo.service;

import com.example.demo.dao.QuotaPolicyDocumentEntryMapper;
import com.example.demo.domain.QuotaPolicyDocumentEntryKey;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * Created by guorunqi on 2019/3/11.
 */
@Service
public class QuotaPolicyDocumentEntryService {
    @Resource
    private QuotaPolicyDocumentEntryMapper quotaPolicyDocumentEntryMapper;
    public Boolean insertQuotaPolicyDocumentEntry(QuotaPolicyDocumentEntryKey quotaPolicyDocumentEntryKey){
        try {
            quotaPolicyDocumentEntryMapper.insert(quotaPolicyDocumentEntryKey);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public Boolean deleteQuotaPolicyDocumentEntrys(List<QuotaPolicyDocumentEntryKey> list){
        try {
            for(QuotaPolicyDocumentEntryKey quotaPolicyDocumentEntryKey:list){
                quotaPolicyDocumentEntryMapper.deleteByPrimaryKey(quotaPolicyDocumentEntryKey);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public List<HashMap> queryQuotaPolicyDocumentEntryByQuotaID(String id){
        return quotaPolicyDocumentEntryMapper.queryQuotaPolicyDocumentEntryByQuotaID(id);
    }
}
