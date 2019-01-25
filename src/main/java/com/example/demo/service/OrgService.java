package com.example.demo.service;

import com.example.demo.dao.OrganizationMapper;
import com.example.demo.domain.Organization;
import com.example.demo.domain.OrganizationExample;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

    @Service
    public class OrgService {
    @Resource
    private OrganizationMapper organizationMapper;
    /**
     * 查询所有机构
     * @return
     */
      public List<Organization> selectAllProject(){
          List<Organization> organizationList = organizationMapper.selectByExample(new OrganizationExample());
            if(organizationList.size() > 0){
                return  organizationList;
            }
           return  null;
        }
}
