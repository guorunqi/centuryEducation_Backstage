package com.example.demo.dao;

import com.example.demo.domain.QuotaProblemExample;
import com.example.demo.domain.QuotaProblemKey;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface QuotaProblemMapper {
    int countByExample(QuotaProblemExample example);

    int deleteByExample(QuotaProblemExample example);

    int deleteByPrimaryKey(QuotaProblemKey key);

    int insert(QuotaProblemKey record);

    int insertSelective(QuotaProblemKey record);

    List<QuotaProblemKey> selectByExample(QuotaProblemExample example);

    int updateByExampleSelective(@Param("record") QuotaProblemKey record, @Param("example") QuotaProblemExample example);

    int updateByExample(@Param("record") QuotaProblemKey record, @Param("example") QuotaProblemExample example);

    List<HashMap> queryQuotaProblemByQuotaID(@Param("id") String id);
}