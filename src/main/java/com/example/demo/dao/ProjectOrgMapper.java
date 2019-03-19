package com.example.demo.dao;

import com.example.demo.domain.ProjectOrg;
import com.example.demo.domain.ProjectOrgExample;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectOrgMapper {
    int countByExample(ProjectOrgExample example);

    int deleteByExample(ProjectOrgExample example);

    int deleteByPrimaryKey(String id);

    int insert(ProjectOrg record);

    int insertSelective(ProjectOrg record);

    List<ProjectOrg> selectByExample(ProjectOrgExample example);

    ProjectOrg selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ProjectOrg record, @Param("example") ProjectOrgExample example);

    int updateByExample(@Param("record") ProjectOrg record, @Param("example") ProjectOrgExample example);

    int updateByPrimaryKeySelective(ProjectOrg record);

    int updateByPrimaryKey(ProjectOrg record);

    List<HashMap> queryProjectOrgByProjectID(@Param("projectID") String projectID);

    List<HashMap> queryProjectOrgByProjectIDAndQuotaID(@Param("projectID") String projectID,@Param("quotaID") String quotaID);


}