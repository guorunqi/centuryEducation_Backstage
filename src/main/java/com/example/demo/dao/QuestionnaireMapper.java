package com.example.demo.dao;

import com.example.demo.domain.Questionnaire;
import com.example.demo.domain.QuestionnaireExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface QuestionnaireMapper {
    int countByExample(QuestionnaireExample example);

    int deleteByExample(QuestionnaireExample example);

    int deleteByPrimaryKey(String id);

    int insert(Questionnaire record);

    int insertSelective(Questionnaire record);

    List<Questionnaire> selectByExample(QuestionnaireExample example);

    List<Questionnaire> selectQuestionnaireManagementList(@Param("name") String name,@Param("proName") String proName,@Param("crowdOriented") String crowdOriented,@Param("type") String type);

    Questionnaire selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Questionnaire record, @Param("example") QuestionnaireExample example);

    int updateByExample(@Param("record") Questionnaire record, @Param("example") QuestionnaireExample example);

    int updateByPrimaryKeySelective(Questionnaire record);

    int updateByPrimaryKey(Questionnaire record);
}