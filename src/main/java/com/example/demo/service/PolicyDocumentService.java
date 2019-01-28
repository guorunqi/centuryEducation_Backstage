package com.example.demo.service;

import com.example.demo.dao.PolicyDocumentMapper;
import com.example.demo.domain.PolicyDocument;
import com.example.demo.domain.PolicyDocumentExample;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;


@Service
public class PolicyDocumentService {
    @Resource
    private PolicyDocumentMapper policyDocumentMapper;
    /**
     * 查询所有政策文件
     * @return
     */
    public List<PolicyDocument> selectAllPolicyDocument(){
        PolicyDocumentExample policyDocumentExample = new PolicyDocumentExample();
        policyDocumentExample.or().andIdEqualTo("1");
        List<PolicyDocument> selectAllPolicyDocumentList = policyDocumentMapper.selectByExample(policyDocumentExample);
        if(selectAllPolicyDocumentList.size() > 0){
            return  selectAllPolicyDocumentList;
        }
        return  null;
    }

    /**
     * 根据ID查询政策文件
     * @return
     */
    public PolicyDocument selectPolicyDocumentById(String id){
        return policyDocumentMapper.selectByPrimaryKey(id);


    }
}
