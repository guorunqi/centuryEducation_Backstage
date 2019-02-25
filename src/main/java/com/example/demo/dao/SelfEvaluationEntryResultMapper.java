package com.example.demo.dao;

import com.example.demo.domain.SelfEvaluationEntryResult;
import com.example.demo.domain.SelfEvaluationEntryResultExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SelfEvaluationEntryResultMapper {
    int countByExample(SelfEvaluationEntryResultExample example);

    int deleteByExample(SelfEvaluationEntryResultExample example);

    int deleteByPrimaryKey(String id);

    int insert(SelfEvaluationEntryResult record);

    int insertSelective(SelfEvaluationEntryResult record);

    List<SelfEvaluationEntryResult> selectByExampleWithBLOBs(SelfEvaluationEntryResultExample example);

    List<SelfEvaluationEntryResult> selectByExample(SelfEvaluationEntryResultExample example);

    SelfEvaluationEntryResult selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SelfEvaluationEntryResult record, @Param("example") SelfEvaluationEntryResultExample example);

    int updateByExampleWithBLOBs(@Param("record") SelfEvaluationEntryResult record, @Param("example") SelfEvaluationEntryResultExample example);

    int updateByExample(@Param("record") SelfEvaluationEntryResult record, @Param("example") SelfEvaluationEntryResultExample example);

    int updateByPrimaryKeySelective(SelfEvaluationEntryResult record);

    int updateByPrimaryKeyWithBLOBs(SelfEvaluationEntryResult record);

    int updateByPrimaryKey(SelfEvaluationEntryResult record);
}