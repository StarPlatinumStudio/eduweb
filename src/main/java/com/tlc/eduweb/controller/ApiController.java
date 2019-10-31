package com.tlc.eduweb.controller;

import com.tlc.eduweb.modles.entity.*;
import com.tlc.eduweb.modles.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.rmi.runtime.Log;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import static org.apache.commons.lang3.StringEscapeUtils.escapeHtml4;
import static org.apache.commons.lang3.StringEscapeUtils.unescapeHtml4;

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
    @Autowired
    private HomeWorkRepository homeWorkRepository;
    @Autowired
    private UserHomeWorkRepository userHomeWorkRepository;
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
    @GetMapping("/allhomework")
    public List<HomeWork> allHomeWork(){
        return homeWorkRepository.findAll();
    }
    @GetMapping("/alluserhomework")
    public List<UserHomeWork> allUserHomeWork(){
        return userHomeWorkRepository.findAll();
    }
    @GetMapping("/userhomework")
    public List<UserHomeWork> userHomeWork(int id){
        List<UserHomeWork> userHomeWorks=new ArrayList<>();
        for (UserHomeWork userHomeWork:userHomeWorkRepository.findAll()) {
            if (userHomeWork.getHomework() == id) userHomeWorks.add(userHomeWork);
        }
        return userHomeWorks;
    }
    @GetMapping("/myhomework")
    public List<UserHomeWork> myHomeWork(String openid){
        List<UserHomeWork> userHomeWorks=new ArrayList<>();
        for (UserHomeWork userHomeWork:userHomeWorkRepository.findAll()) {
            if (userHomeWork.getOpenid().equals(openid)) userHomeWorks.add(userHomeWork);
        }
        return userHomeWorks;
    }
    @GetMapping("/myclass")
    public TheClass myClass(String theclass){
        List<User> users=new ArrayList<>();
        for (TheClass item:theClassRepository.findAll()) {
            if (item.getLabel().equals(theclass)) return item;
        }
        return null;
    }
    @GetMapping("/classhomework")
    public ResponseMsg classHomeWork(String theclass){
        if (theclass.isEmpty()){
            return new ResponseMsg("没有找到班级信息，请退出页面从公众号重新登录",-1,null);
        }
        List<HomeWork> classHomeWork=new ArrayList<>();
        for (HomeWork homework:homeWorkRepository.findAll()) {
            if(homework.getTheclass().equals(theclass)) {
               // homework.setDetial(homework.getDetial().replaceAll("\r|\n", "<br/>"));
                classHomeWork.add(homework);}
        }
        return new ResponseMsg("成功",0,classHomeWork);
    }
    @RequestMapping("/commenthomework")
    public ResponseMsg commentHomeWork(int id,String commentadmin,String comment ,String commenttime){
        ResponseMsg ret=new ResponseMsg();
        try {
            UserHomeWork userHomeWork=new UserHomeWork();
            for (UserHomeWork item:userHomeWorkRepository.findAll()) {
                if (item.getId() == id) userHomeWork=item;
                continue;
            }
           userHomeWork.setCommentadmin(commentadmin);
           userHomeWork.setCommenttime(commenttime);
           userHomeWork.setComment(comment);
           userHomeWorkRepository.save(userHomeWork);
        }
        catch (Exception e){
            return new ResponseMsg(e.getMessage(),-1,null);
        }
        ret.setMsg("存储成功");
        ret.setRet(0);
        return ret;
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

    @RequestMapping("/savehomework")
    public ResponseMsg saveHomeWork(@Valid @RequestBody HomeWork homeWork){
        ResponseMsg ret=new ResponseMsg();
        try {
            ret.setData(homeWorkRepository.save(homeWork));
        }
        catch (Exception e){
            return new ResponseMsg(e.getMessage(),-1,null);
        }
        ret.setMsg("存储成功");
        ret.setRet(0);
        return ret;
    }
    @RequestMapping("/saveuserhomework")
    public ResponseMsg saveUserHomeWork(@Valid @RequestBody UserHomeWork userhomeWork){
        ResponseMsg ret=new ResponseMsg();
        try {
            ret.setData(userHomeWorkRepository.save(userhomeWork));
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
