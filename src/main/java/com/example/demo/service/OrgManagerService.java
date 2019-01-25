package com.example.demo.service;

import com.example.demo.dao.OrganizationMapper;
import com.example.demo.domain.Organization;
import com.example.demo.domain.OrganizationExample;
import com.example.demo.util.TreePojo;
import com.example.demo.util.TreeUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.function.BooleanSupplier;

/**
 * Created by guorunqi on 2019/1/22.
 */
@Service
public class OrgManagerService {
    @Resource
    private OrganizationMapper organizationMapper;
    public List<TreePojo> queryAllOrganization(){
        OrganizationExample organizationExample=new OrganizationExample();
        List<Organization> orgs= organizationMapper.selectByExample(organizationExample);
        TreeUtil treeUtil=new TreeUtil();
        List<TreePojo> list=treeUtil.OrgToTree(orgs);
        return treeUtil.getTree(list);
    }
    public Boolean saveOrganization(Organization organization){
        if(organization.getId()!=null&&!organization.getId().equals("")){
            organizationMapper.updateByPrimaryKeySelective(organization);
        }else {
            organizationMapper.insertSelective(organization);
        }
        return true;
    }
}
