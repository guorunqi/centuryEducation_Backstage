package com.example.demo.service;

import com.example.demo.dao.QuotaMapper;
import com.example.demo.dao.QuotaResultMapper;
import com.example.demo.domain.QuotaResult;
import com.example.demo.domain.QuotaResultExample;
import com.example.demo.domain.QuotaResultWithBLOBs;
import com.example.demo.util.CommonUtil;
import com.example.demo.util.TreeTablePojo;
import com.example.demo.util.TreeTableUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
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
            e.printStackTrace();
            return false;
        }
    }
    public Boolean deleteQuotaResultByQuotaId(String quotaId){
        try {
                if(!StringUtils.isBlank(quotaId)){
                    QuotaResultExample quotaResultExample=new QuotaResultExample();
                    quotaResultExample.or().andQuotaIdEqualTo(quotaId);
                    quotaResultMapper.deleteByExample(quotaResultExample);
                }else{
                    return true;
                }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public List<TreeTablePojo> queryQuotaResultTreeTable(String assessmentId,String projectOrgId){
        try {
            if(!StringUtils.isBlank(assessmentId)&&!StringUtils.isBlank(projectOrgId)){
                List<HashMap> list=quotaResultMapper.queryQuotaResultByAssessmentIdAndProjectOrgId(assessmentId,projectOrgId);
                TreeTableUtil treeTableUtil=new TreeTableUtil();
                List<TreeTablePojo> pojoList=treeTableUtil.HashMapToTree(list);
                pojoList=treeTableUtil.getTree(pojoList);
                return pojoList;
            }else{
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public String verificationTreeTablePojo(List<TreeTablePojo> list){
        for(TreeTablePojo pojo:list){
            if(pojo.getChildren().size()>0){
                String value=this.verificationTreeTablePojo(pojo.getChildren());
                if(!StringUtils.isBlank(value)){
                    return value;
                }
            }else if(StringUtils.isBlank(pojo.getName())){
                return "指标ID:"+pojo.getId()+"没有指标结果";
            }else if(StringUtils.isBlank(pojo.exped1)){
                return "指标ID:"+pojo.getId()+"没有指标权重信息";
            }else if(StringUtils.isBlank(pojo.exped2)){
                return "指标ID:"+pojo.getId()+"指标结果没有打分";
            }
        }
        return "";
    }
    public float calculationNumber(List<TreeTablePojo> list,float number){
        for(TreeTablePojo pojo:list){
            if(pojo.getChildren().size()>0){
                number=this.calculationNumber(pojo.getChildren(),number);
            }else{
                number+=Float.parseFloat(pojo.getExped1())*Float.parseFloat(pojo.getExped2());
            }
        }
        return number;
    }
}
