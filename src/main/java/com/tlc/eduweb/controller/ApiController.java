package com.tlc.eduweb.controller;

import com.alibaba.fastjson.JSON;
import com.tlc.eduweb.modles.entity.*;
import com.tlc.eduweb.modles.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private AdministratorsRepository administratorsRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SchoolRepository schoolRepository;
    @Autowired
    private TheClassRepository theClassRepository;
    @Autowired
    private VipRequestRepository vipRequestRepository;
    @GetMapping("/login")
    public ResponseMsg login(String account,String password){
        ResponseMsg res=new ResponseMsg();
       List<Administrator> admins= administratorsRepository.findAll();
       for (Administrator admin:admins){
           if(admin.getAccount().equals(account)){
               if (admin.getPassword().equals(password))
               {
                   res.setRet(0);
                   res.setMsg("登录成功");
                   res.setData(admin);
               }else {
                   res.setRet(-1);
                   res.setMsg("密码错误");
               }
               return res;
           }
       }
       return new ResponseMsg("没有找到该账号",-1,"");
    }
    @GetMapping("/alladmin")
    public List<Administrator> allAdmin(){
        return administratorsRepository.findAll();
    }
    @RequestMapping("/administrator")
    public ResponseMsg administrator(@Valid @RequestBody Administrator admin){
        try {
            administratorsRepository.save(admin);
        }
        catch (Exception ex){
            return new ResponseMsg(ex.getMessage(),-1,null);
        }
        return new ResponseMsg("信息更改成功",0,null);
    }
    @RequestMapping("/newadmin")
    public ResponseMsg newAdmin(@Valid @RequestBody Administrator admin){
        List<Administrator> admins= administratorsRepository.findAll();
        for (Administrator item:admins){
            if(item.getName().equals(admin.getName())){
                return new ResponseMsg("失败：已经存在名称为【"+admin.getName()+"】的管理员",-1,null);
            }
            if(item.getAccount().equals(admin.getAccount())){
                return new ResponseMsg("失败：已经存在登录账户为【"+admin.getAccount()+"】的管理员",-1,null);
            }
        }
        try {
            administratorsRepository.save(admin);
        }
        catch (Exception ex){
            return new ResponseMsg(ex.getMessage(),-1,null);
        }
        return new ResponseMsg("成功添加新管理员",0,null);
    }
    @RequestMapping("/manageadmin")
    public ResponseMsg manageAdmin(@Valid @RequestBody Administrator admin){
        try {
            administratorsRepository.save(admin);
        }
        catch (Exception ex){
            return new ResponseMsg(ex.getMessage(),-1,null);
        }
        return new ResponseMsg("管理员信息修改成功",0,null);
    }
    @RequestMapping("/deleteadmin")
    public ResponseMsg deleteAdmin(@Valid @RequestBody Administrator administrator){
        administratorsRepository.delete(administrator);
        return new ResponseMsg("删除管理员成功",0,null);
    }
    @GetMapping("/allusers")
    public List<User> allUsers(){
        return userRepository.findAll();
    }
    @GetMapping("/allschools")
    public List<School> allSchools(){
        return schoolRepository.findAll();
    }
    @GetMapping("/alltheclass")
    public List<TheClass> allTheClass(){
        return theClassRepository.findAll();
    }
    @RequestMapping("/saveschool")
    public ResponseMsg saveSchool(@Valid @RequestBody School school){
       ResponseMsg ret=new ResponseMsg();
        try {
            ret.setData(schoolRepository.save(school));
        }
        catch (Exception e){
            return new ResponseMsg(e.getMessage(),-1,null);
        }
       ret.setMsg("存储成功");
       ret.setRet(0);
       return ret;
    }
    @RequestMapping("/savetheclass")
    public ResponseMsg saveTheClass(@Valid @RequestBody TheClass theClass){
        ResponseMsg ret=new ResponseMsg();
        try {
            ret.setData(theClassRepository.save(theClass));
        }
        catch (Exception e){
            return new ResponseMsg(e.getMessage(),-1,null);
        }
        ret.setMsg("存储成功");
        ret.setRet(0);
        return ret;
    }
    @RequestMapping("/deleteschool")
    public ResponseMsg deleteSchool(@Valid @RequestBody School school){
        schoolRepository.delete(school);
        return new ResponseMsg("删除成功",0,null);
    }
    @GetMapping("/allviprequest")
    public List<VipRequest> allVipRequest(){
        return vipRequestRepository.findAll();
    }
    @RequestMapping("/saveviprequest")
    public ResponseMsg vipRequest(@Valid @RequestBody VipRequest vipRequest){
        if(vipRequest.getTheclass() !=null){
           for(User user:userRepository.findAll()){
               if (user.getOpenid().equals(vipRequest.getOpenid())){
                   user.setTheclass(vipRequest.getTheclass());
                   userRepository.save(user);
                   continue;
               }
           }
        }
        vipRequestRepository.save(vipRequest);
        return new ResponseMsg("申请成功",0,null);
    }
    @RequestMapping("/isviprequest")
    public ResponseMsg isVipRequest(String openid){
        ResponseMsg ret=new ResponseMsg();
        List<VipRequest> vipRequests=vipRequestRepository.findAll();
        for (VipRequest vipRequest: vipRequests){
            if(vipRequest.getOpenid().equals(openid)&&vipRequest.getStatu().equals("待审核")){
                ret.setData(vipRequest);
                ret.setRet(0);//找到待审核请求
                return ret;
            }
        }
        return new ResponseMsg("",1,null);//没有符合的请求
    }
}
