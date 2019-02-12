package com.example.demo.service;

import com.example.demo.dao.RoleMapper;
import com.example.demo.domain.Role;
import com.example.demo.domain.RoleExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by guorunqi on 2019/2/12.
 */
@Service
public class RoleService {
    @Resource
    RoleMapper roleMapper;
    public List<Role> queryAllRole(){
        RoleExample example=new RoleExample();
        return roleMapper.selectByExample(example);
    }
}
