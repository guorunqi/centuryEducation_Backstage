package com.example.demo.dao;

import com.example.demo.domain.Problem;
import com.example.demo.domain.ProblemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProblemMapper {
    int countByExample(ProblemExample example);

    int deleteByExample(ProblemExample example);

    int deleteByPrimaryKey(String id);

    int insert(Problem record);

    int insertSelective(Problem record);

    List<Problem> selectByExample(ProblemExample example);

    Problem selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Problem record, @Param("example") ProblemExample example);

    int updateByExample(@Param("record") Problem record, @Param("example") ProblemExample example);

    int updateByPrimaryKeySelective(Problem record);

    int updateByPrimaryKey(Problem record);
}