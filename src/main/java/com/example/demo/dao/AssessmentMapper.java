package com.example.demo.dao;

import com.example.demo.domain.Assessment;
import com.example.demo.domain.AssessmentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AssessmentMapper {
    int countByExample(AssessmentExample example);

    int deleteByExample(AssessmentExample example);

    int deleteByPrimaryKey(String id);

    int insert(Assessment record);

    int insertSelective(Assessment record);

    List<Assessment> selectByExample(AssessmentExample example);

    Assessment selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Assessment record, @Param("example") AssessmentExample example);

    int updateByExample(@Param("record") Assessment record, @Param("example") AssessmentExample example);

    int updateByPrimaryKeySelective(Assessment record);

    int updateByPrimaryKey(Assessment record);
}