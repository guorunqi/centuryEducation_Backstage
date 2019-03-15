package com.example.demo.dao;

import com.example.demo.domain.QuotaPolicyDocumentEntryExample;
import com.example.demo.domain.QuotaPolicyDocumentEntryKey;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface QuotaPolicyDocumentEntryMapper {
    int countByExample(QuotaPolicyDocumentEntryExample example);

    int deleteByExample(QuotaPolicyDocumentEntryExample example);

    int deleteByPrimaryKey(QuotaPolicyDocumentEntryKey key);

    int insert(QuotaPolicyDocumentEntryKey record);

    int insertSelective(QuotaPolicyDocumentEntryKey record);

    List<QuotaPolicyDocumentEntryKey> selectByExample(QuotaPolicyDocumentEntryExample example);

    int updateByExampleSelective(@Param("record") QuotaPolicyDocumentEntryKey record, @Param("example") QuotaPolicyDocumentEntryExample example);

    int updateByExample(@Param("record") QuotaPolicyDocumentEntryKey record, @Param("example") QuotaPolicyDocumentEntryExample example);

    List<HashMap> queryQuotaPolicyDocumentEntryByQuotaID(@Param("id") String id);
}