package com.example.demo.service;

import com.example.demo.dao.QuotaMapper;
import com.example.demo.dao.QuotaResultMapper;
import com.example.demo.domain.QuotaResult;
import com.example.demo.domain.QuotaResultWithBLOBs;
import com.example.demo.util.CommonUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by guorunqi on 2019/3/20.
 */
@Service
public class QuotaResultService {
    @Resource
    private QuotaResultMapper quotaResultMapper;
    public Boolean saveQuotaResults(List<QuotaResultWithBLOBs> list){
        try {
            for(QuotaResultWithBLOBs quotaResultWithBLOBs:list){
                if(StringUtils.isBlank(quotaResultWithBLOBs.getId())){
                    quotaResultWithBLOBs.setId(CommonUtil.getPrimaryKey());
                    quotaResultMapper.insert(quotaResultWithBLOBs);
                }else{
                    quotaResultMapper.updateByPrimaryKey(quotaResultWithBLOBs);
                }
            }
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
