package com.example.demo.dao;

import com.example.demo.domain.answerResult;
import com.example.demo.domain.answerResultExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface answerResultMapper {
    int countByExample(answerResultExample example);

    int deleteByExample(answerResultExample example);

    int deleteByPrimaryKey(String id);

    int insert(answerResult record);

    int insertSelective(answerResult record);

    List<answerResult> selectByExample(answerResultExample example);

    answerResult selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") answerResult record, @Param("example") answerResultExample example);

    int updateByExample(@Param("record") answerResult record, @Param("example") answerResultExample example);

    int updateByPrimaryKeySelective(answerResult record);

    int updateByPrimaryKey(answerResult record);
}