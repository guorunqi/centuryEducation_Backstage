package com.example.demo.dao;

import com.example.demo.domain.SelfEvaluationEntry;
import com.example.demo.domain.SelfEvaluationEntryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SelfEvaluationEntryMapper {
    int countByExample(SelfEvaluationEntryExample example);

    int deleteByExample(SelfEvaluationEntryExample example);

    int deleteByPrimaryKey(String id);

    int insert(SelfEvaluationEntry record);

    int insertSelective(SelfEvaluationEntry record);

    List<SelfEvaluationEntry> selectByExample(SelfEvaluationEntryExample example);

    SelfEvaluationEntry selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SelfEvaluationEntry record, @Param("example") SelfEvaluationEntryExample example);

    int updateByExample(@Param("record") SelfEvaluationEntry record, @Param("example") SelfEvaluationEntryExample example);

    int updateByPrimaryKeySelective(SelfEvaluationEntry record);

    int updateByPrimaryKey(SelfEvaluationEntry record);
}