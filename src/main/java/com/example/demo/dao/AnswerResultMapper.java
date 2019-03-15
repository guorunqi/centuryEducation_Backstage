package com.example.demo.dao;

import com.example.demo.domain.AnswerResult;
import com.example.demo.domain.AnswerResultExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AnswerResultMapper {
    int countByExample(AnswerResultExample example);

    int deleteByExample(AnswerResultExample example);

    int deleteByPrimaryKey(String id);

    int insert(AnswerResult record);

    int insertSelective(AnswerResult record);

    List<AnswerResult> selectByExample(AnswerResultExample example);

    AnswerResult selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") AnswerResult record, @Param("example") AnswerResultExample example);

    int updateByExample(@Param("record") AnswerResult record, @Param("example") AnswerResultExample example);

    int updateByPrimaryKeySelective(AnswerResult record);

    int updateByPrimaryKey(AnswerResult record);
}