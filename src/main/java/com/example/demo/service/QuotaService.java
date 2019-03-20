package com.example.demo.service;

import com.example.demo.dao.QuotaMapper;
import com.example.demo.domain.Quota;
import com.example.demo.domain.QuotaExample;
import com.example.demo.util.CommonUtil;
import com.example.demo.util.TreeTablePojo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by guorunqi on 2019/2/28.
 */
@Service
public class QuotaService {
    @Resource
    private QuotaMapper quotaMapper;
    public List<Quota> queryQuotaByAssessmentId(String assessmentId){
        QuotaExample quotaExample=new QuotaExample();
        quotaExample.or().andAssessmentIdEqualTo(assessmentId);
        return quotaMapper.selectByExample(quotaExample);
    }
    public Quota queryQuotaById(String id){
        return quotaMapper.selectByPrimaryKey(id);
    }
    public Boolean deleteQuota(TreeTablePojo treeTablePojo){
        try {
            if(treeTablePojo.children.size()>0){
                for(TreeTablePojo quota:treeTablePojo.children){
                    deleteQuota(quota);
                }
            }
            quotaMapper.deleteByPrimaryKey(treeTablePojo.getId());
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }
    public Quota saveQuota(Quota quota){
        try {
            if(StringUtils.isBlank(quota.getId())){
                quota.setId(CommonUtil.getPrimaryKey());
                quotaMapper.insert(quota);
            }else {
                quotaMapper.updateByPrimaryKeySelective(quota);
            }
            return quota;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
}
