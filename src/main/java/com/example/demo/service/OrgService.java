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
        /**
         * 根据code查询机构
         * @return
         */
        public Organization selectOrgBycode(String code){
            OrganizationExample org = new OrganizationExample();
            org.or().andCodeEqualTo(code);
            List<Organization> organizationList = organizationMapper.selectByExample(org);
            if(organizationList.size() == 1){
                Organization o = organizationList.get(0);
                return  o;
            }
            return  null;
        }

        /**
         * 根据id查询机构
         * @return
         */
        public Organization selectOrgByOrgId(String id){
            return  organizationMapper.selectByPrimaryKey(id);
        }
        public List<Organization> queryOrgByProjectID(String id){
            return organizationMapper.queryOrgByProjectID(id);
        }
}
