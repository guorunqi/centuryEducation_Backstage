package com.example.demo.dao;

import com.example.demo.domain.QuotaSelfEvaluationEntryExample;
import com.example.demo.domain.QuotaSelfEvaluationEntryKey;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface QuotaSelfEvaluationEntryMapper {
    int countByExample(QuotaSelfEvaluationEntryExample example);

    int deleteByExample(QuotaSelfEvaluationEntryExample example);

    int deleteByPrimaryKey(QuotaSelfEvaluationEntryKey key);

    int insert(QuotaSelfEvaluationEntryKey record);

    int insertSelective(QuotaSelfEvaluationEntryKey record);

    List<QuotaSelfEvaluationEntryKey> selectByExample(QuotaSelfEvaluationEntryExample example);

    int updateByExampleSelective(@Param("record") QuotaSelfEvaluationEntryKey record, @Param("example") QuotaSelfEvaluationEntryExample example);

    int updateByExample(@Param("record") QuotaSelfEvaluationEntryKey record, @Param("example") QuotaSelfEvaluationEntryExample example);

    List<HashMap> queryQuotaSelfEvaluationEntryByQuotaID(@Param("id") String id);
}