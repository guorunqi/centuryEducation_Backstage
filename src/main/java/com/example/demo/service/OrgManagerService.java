package com.example.demo.service;

import com.example.demo.dao.OrganizationMapper;
import com.example.demo.domain.Organization;
import com.example.demo.domain.OrganizationExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by guorunqi on 2019/1/22.
 */
@Service
public class OrgManagerService {
    @Resource
    private OrganizationMapper organizationMapper;
    public List<Organization> queryAllOrganization(){
        OrganizationExample organizationExample=new OrganizationExample();
        return organizationMapper.selectByExample(organizationExample);
    }
}
