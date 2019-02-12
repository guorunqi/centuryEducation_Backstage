package com.example.demo.dao;

import com.example.demo.domain.OrgUser;
import com.example.demo.domain.OrgUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrgUserMapper {
    int countByExample(OrgUserExample example);

    int deleteByExample(OrgUserExample example);

    int deleteByPrimaryKey(String id);

    int insert(OrgUser record);

    int insertSelective(OrgUser record);

    List<OrgUser> selectByExample(OrgUserExample example);

    OrgUser selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") OrgUser record, @Param("example") OrgUserExample example);

    int updateByExample(@Param("record") OrgUser record, @Param("example") OrgUserExample example);

    int updateByPrimaryKeySelective(OrgUser record);

    int updateByPrimaryKey(OrgUser record);
}