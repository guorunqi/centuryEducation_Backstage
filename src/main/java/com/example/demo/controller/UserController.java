package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.domain.ControllerReturn;
import com.example.demo.domain.OrgUser;
import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by guorunqi on 2019/2/12.
 */
@Controller
public class UserController {
    @Autowired
    UserService userService;

    @ResponseBody
    @RequestMapping(value = "/queryUserByOrg",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public ControllerReturn queryUsersByOrgID(@RequestBody String data){

        ControllerReturn controllerReturn=new ControllerReturn();
        try{
            String orgid= JSONObject.parseObject(data).get("id").toString();
            if (orgid!=null&&!orgid.equals("")){
                List<User> list=userService.queryUserByOrgId(orgid);
                controllerReturn.setCode("true");
                controllerReturn.setData(list);
            }else{
                controllerReturn.setCode("false");
                controllerReturn.setMessage("组织机构id为空");
            }

        }catch (Exception e){
            controllerReturn.setCode("false");
            controllerReturn.setMessage(e.toString());
            e.printStackTrace();
        }
        return controllerReturn;
    }
    @ResponseBody
    @RequestMapping(value = "/saveUser",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public ControllerReturn saveUser(@RequestBody String data){

        ControllerReturn controllerReturn=new ControllerReturn();
        try{
            String jsonString=JSONObject.parseObject(data).get("data").toString();
            String orgid=JSONObject.parseObject(data).get("orgid").toString();
            User user=JSONObject.parseObject(jsonString,User.class);
            if(orgid!=null&&!orgid.equals("")){
                OrgUser orgUser=new OrgUser();
                orgUser.setOrgId(orgid);
                Boolean sign=userService.saveUserAndOrgUser(user,orgUser);

                controllerReturn.setCode("true");
                controllerReturn.setData(sign);
            }
        }catch (Exception e){
            controllerReturn.setCode("false");
            controllerReturn.setMessage(e.toString());
            e.printStackTrace();
        }
        return controllerReturn;
    }
    @ResponseBody
    @RequestMapping(value = "/deleteUser",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public ControllerReturn deleteUser(@RequestBody String data){

        ControllerReturn controllerReturn=new ControllerReturn();
        try{
            String jsonString=JSONObject.parseObject(data).get("data").toString();
            String orgid=JSONObject.parseObject(data).get("orgid").toString();
            List<User> users= JSON.parseArray(jsonString,User.class);
            if(orgid!=null&&!orgid.equals("")){
                Boolean sign=userService.deleteUserAndOrgUser(users,orgid);

                controllerReturn.setCode("true");
                controllerReturn.setData(sign);
            }
        }catch (Exception e){
            controllerReturn.setCode("false");
            controllerReturn.setMessage(e.toString());
            e.printStackTrace();
        }
        return controllerReturn;
    }
}
