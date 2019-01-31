package com.example.demo.dao;

import com.example.demo.domain.PolicyDocumentEntry;
import com.example.demo.domain.PolicyDocumentEntryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PolicyDocumentEntryMapper {
    int countByExample(PolicyDocumentEntryExample example);

    int deleteByExample(PolicyDocumentEntryExample example);

    int deleteByPrimaryKey(String id);

    int insert(PolicyDocumentEntry record);

    int insertSelective(PolicyDocumentEntry record);

    List<PolicyDocumentEntry> selectByExampleWithBLOBs(PolicyDocumentEntryExample example);

    List<PolicyDocumentEntry> selectByExample(PolicyDocumentEntryExample example);

    PolicyDocumentEntry selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PolicyDocumentEntry record, @Param("example") PolicyDocumentEntryExample example);

    int updateByExampleWithBLOBs(@Param("record") PolicyDocumentEntry record, @Param("example") PolicyDocumentEntryExample example);

    int updateByExample(@Param("record") PolicyDocumentEntry record, @Param("example") PolicyDocumentEntryExample example);

    int updateByPrimaryKeySelective(PolicyDocumentEntry record);

    int updateByPrimaryKeyWithBLOBs(PolicyDocumentEntry record);

    int updateByPrimaryKey(PolicyDocumentEntry record);
}