package com.example.demo.service;

import com.example.demo.dao.DictEntryMapper;
import com.example.demo.domain.DictEntry;
import com.example.demo.domain.DictEntryExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CommonService {
    @Resource
    private DictEntryMapper dictEntryMapper;
    public List<DictEntry> getDictAllByDictTypeId(String dictTypeId){
        DictEntryExample dictEntryExample = new DictEntryExample();
        dictEntryExample.or().andDictTypeIdEqualTo(dictTypeId);
        List<DictEntry> dictEntryMapperList = dictEntryMapper.selectByExample(dictEntryExample);
        if(dictEntryMapperList.size()>0){
            return dictEntryMapperList;
        }else{
            return null;
        }
    }
}