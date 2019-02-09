package com.example.demo.service;

import com.example.demo.dao.PolicyDocumentEntryMapper;
import com.example.demo.dao.PolicyDocumentMapper;
import com.example.demo.domain.PolicyDocumentEntry;
import com.example.demo.domain.PolicyDocumentEntryExample;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class PolicyDocumentEntryService {
    @Resource
    private PolicyDocumentMapper policyDocumentMapper;
    @Resource
    private PolicyDocumentEntryMapper policyDocumentEntryMapper;



    /**
     * 根据id查询政策文件
     * @return
     */
    public PolicyDocumentEntry selectPolicyDocumentEntryByPrimaryKey(String id){
        return  policyDocumentEntryMapper.selectByPrimaryKey(id);
    }
    /**
     * 根据模板查询政策文件
     * @return
     */
    public List<PolicyDocumentEntry> selectPolicyDocumentEntryByExample(PolicyDocumentEntryExample policyDocumentEntryExample){
        return  policyDocumentEntryMapper.selectByExampleWithBLOBs(policyDocumentEntryExample);
    }

    public int  insertPolicyDocumentEntry(PolicyDocumentEntry oolicyDocumentEntry){
        return  policyDocumentEntryMapper.insert(oolicyDocumentEntry);
    }

    public int  upDataPolicyDocumentEntry(PolicyDocumentEntry oolicyDocumentEntry){
        return  policyDocumentEntryMapper.updateByPrimaryKeyWithBLOBs(oolicyDocumentEntry);
    }

    public int deleteSubordinatePolicyDocumentEntryById(String id) {
        PolicyDocumentEntry contentCategory = policyDocumentEntryMapper.selectByPrimaryKey(id);
        PolicyDocumentEntryExample example = new PolicyDocumentEntryExample();
        example.or().andParentIdEqualTo(id);

        List<PolicyDocumentEntry> children = policyDocumentEntryMapper.selectByExampleWithBLOBs(example);
        if(children.size() > 0){
            for (PolicyDocumentEntry policyDocumentEntry : children) {
                deleteSubordinatePolicyDocumentEntryById(policyDocumentEntry.getId());
            }
        }
        return policyDocumentEntryMapper.deleteByPrimaryKey(id);
    }
}
