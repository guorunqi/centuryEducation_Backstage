package com.example.demo.dao;

import com.example.demo.domain.DictEntry;
import com.example.demo.domain.DictEntryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DictEntryMapper {
    int countByExample(DictEntryExample example);

    int deleteByExample(DictEntryExample example);

    int deleteByPrimaryKey(String id);

    int insert(DictEntry record);

    int insertSelective(DictEntry record);

    List<DictEntry> selectByExample(DictEntryExample example);

    DictEntry selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") DictEntry record, @Param("example") DictEntryExample example);

    int updateByExample(@Param("record") DictEntry record, @Param("example") DictEntryExample example);

    int updateByPrimaryKeySelective(DictEntry record);

    int updateByPrimaryKey(DictEntry record);
}