package com.example.demo.dao;

import com.example.demo.domain.OrgUserExample;
import com.example.demo.domain.OrgUserKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrgUserMapper {
    int countByExample(OrgUserExample example);

    int deleteByExample(OrgUserExample example);

    int deleteByPrimaryKey(OrgUserKey key);

    int insert(OrgUserKey record);

    int insertSelective(OrgUserKey record);

    List<OrgUserKey> selectByExample(OrgUserExample example);

    int updateByExampleSelective(@Param("record") OrgUserKey record, @Param("example") OrgUserExample example);

    int updateByExample(@Param("record") OrgUserKey record, @Param("example") OrgUserExample example);
}