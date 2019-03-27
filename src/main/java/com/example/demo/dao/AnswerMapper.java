package com.example.demo.dao;

import com.example.demo.domain.Answer;
import com.example.demo.domain.AnswerExample;
import java.util.List;

import com.example.demo.domain.AnswerRate;
import org.apache.ibatis.annotations.Param;

public interface AnswerMapper {
    int countByExample(AnswerExample example);

    int deleteByExample(AnswerExample example);

    int deleteByPrimaryKey(String id);

    int insert(Answer record);

    int insertSelective(Answer record);

    List<Answer> selectByExample(AnswerExample example);

    List<AnswerRate> selectSelectionRate(@Param("problemId")String problemId,@Param("projectOrgId")String projectOrgId);

    Answer selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Answer record, @Param("example") AnswerExample example);

    int updateByExample(@Param("record") Answer record, @Param("example") AnswerExample example);

    int updateByPrimaryKeySelective(Answer record);

    int updateByPrimaryKey(Answer record);
}