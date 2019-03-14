package com.example.demo.dao;

import com.example.demo.domain.SelfEvaluation;
import com.example.demo.domain.SelfEvaluationExample;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SelfEvaluationMapper {
    int countByExample(SelfEvaluationExample example);

    int deleteByExample(SelfEvaluationExample example);

    int deleteByPrimaryKey(String id);

    int insert(SelfEvaluation record);

    int insertSelective(SelfEvaluation record);

    List<SelfEvaluation> selectByExample(SelfEvaluationExample example);

    SelfEvaluation selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SelfEvaluation record, @Param("example") SelfEvaluationExample example);

    int updateByExample(@Param("record") SelfEvaluation record, @Param("example") SelfEvaluationExample example);

    int updateByPrimaryKeySelective(SelfEvaluation record);

    int updateByPrimaryKey(SelfEvaluation record);
    List<HashMap> querySelfEvaluationDataByID(@Param("id") String id);
    List<HashMap> queryPolicyDocumentEntryByID(@Param("id") String id);
    List<HashMap> queryProblemByID(@Param("id") String id);
    List<SelfEvaluation> querySelfEvaluation(@Param("name") String name,@Param("projectName") String projectName);
}