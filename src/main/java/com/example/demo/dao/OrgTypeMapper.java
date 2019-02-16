package com.example.demo.dao;

import com.example.demo.domain.OrgType;
import com.example.demo.domain.OrgTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrgTypeMapper {
    int countByExample(OrgTypeExample example);

    int deleteByExample(OrgTypeExample example);

    int deleteByPrimaryKey(String id);

    int insert(OrgType record);

    int insertSelective(OrgType record);

    List<OrgType> selectByExample(OrgTypeExample example);

    OrgType selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") OrgType record, @Param("example") OrgTypeExample example);

    int updateByExample(@Param("record") OrgType record, @Param("example") OrgTypeExample example);

    int updateByPrimaryKeySelective(OrgType record);

    int updateByPrimaryKey(OrgType record);
}