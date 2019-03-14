package com.example.demo.service;

import com.example.demo.dao.QuotaProblemMapper;
import com.example.demo.domain.QuotaProblemKey;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * Created by guorunqi on 2019/3/11.
 */
@Service
public class QuotaProblemService {
    @Resource
    private QuotaProblemMapper quotaProblemMapper;
    public Boolean insertQuotaProblem(QuotaProblemKey quotaProblemKey){
        try {
            quotaProblemMapper.insert(quotaProblemKey);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public Boolean deleteQuotaProblems(List<QuotaProblemKey> list){
        try {
            for(QuotaProblemKey quotaProblemKey:list){
                quotaProblemMapper.deleteByPrimaryKey(quotaProblemKey);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public List<HashMap> queryQuotaProblemByQuotaID(String id){
        return quotaProblemMapper.queryQuotaProblemByQuotaID(id);
    }
}
