package com.example.demo.dao;

import com.example.demo.domain.ProjectOrgUser;
import com.example.demo.domain.ProjectOrgUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectOrgUserMapper {
    int countByExample(ProjectOrgUserExample example);

    int deleteByExample(ProjectOrgUserExample example);

    int deleteByPrimaryKey(String id);

    int insert(ProjectOrgUser record);

    int insertSelective(ProjectOrgUser record);

    List<ProjectOrgUser> selectByExample(ProjectOrgUserExample example);

    ProjectOrgUser selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ProjectOrgUser record, @Param("example") ProjectOrgUserExample example);

    int updateByExample(@Param("record") ProjectOrgUser record, @Param("example") ProjectOrgUserExample example);

    int updateByPrimaryKeySelective(ProjectOrgUser record);

    int updateByPrimaryKey(ProjectOrgUser record);
}