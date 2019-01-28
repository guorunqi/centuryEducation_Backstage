package com.example.demo.service;

import com.example.demo.dao.OrgUserMapper;
import com.example.demo.dao.OrganizationMapper;
import com.example.demo.domain.OrgUserExample;
import com.example.demo.domain.OrgUserKey;
import com.example.demo.domain.Organization;
import com.example.demo.domain.OrganizationExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrgUserService {
@Resource
private OrgUserMapper orgUserMapper;

    /**
     * 插入数据
     * @param orgUser
     * @return
     */
    public int insertOrgUser(OrgUserKey orgUser){
        return  orgUserMapper.insert(orgUser);
    }


    /**
     * 根据orgId删除数据
     * @param orgId
     * @return
     */
    public int deleteOrgUser(String orgId){
        OrgUserExample orgUserExample = new OrgUserExample();
        orgUserExample.or().andOrgIdEqualTo(orgId);
        return  orgUserMapper.deleteByExample(orgUserExample);
    }
}
