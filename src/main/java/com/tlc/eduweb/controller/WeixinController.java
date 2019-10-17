package com.tlc.eduweb.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tlc.eduweb.modles.entity.User;
import com.tlc.eduweb.modles.repository.UserRepository;
import com.tlc.eduweb.modles.util.HttpClientUtils;
import com.tlc.eduweb.modles.util.SignUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import weixin.popular.api.MenuAPI;
import weixin.popular.api.MessageAPI;
import weixin.popular.api.TokenAPI;
import weixin.popular.bean.menu.Button;
import weixin.popular.bean.menu.MenuButtons;
import weixin.popular.bean.message.massmessage.MassTextMessage;
import weixin.popular.bean.token.Token;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/weixin")
    public class WeixinController {
    @Autowired
    private UserRepository userRepository;
   @Value("${oauth.weixin.appid}")
    private String appId;
    @Value("${oauth.weixin.appsecret}")
    private String appSecret;
    @Value("${oauth.weixin.springurl}")
    private String springUrl;
    @Value("${oauth.weixin.vueurl}")
    private String vueUrl;
    private String access_Token="";
    private Date starDate;
    private Token token;
    private static Logger logger = LoggerFactory.getLogger(HttpClientUtils.class); // 日志记录

    @GetMapping("/menuinit")
    public void menuInit(){
        Button sub1 = new Button();
        sub1.setType("view");
        sub1.setName("教育资源");
        sub1.setUrl("http://www.teresalanguagecenter.cn/");

        Button sub2 = new Button();
        sub2.setType("view");
        sub2.setName("资讯平台");
        sub2.setUrl("http://robinwang.nat300.top/weixin/login");

        Button sub3_1 = new Button();
        sub3_1.setType("scancode_push");
        sub3_1.setName("扫码带提示");
        sub3_1.setKey("rselfmenu_0_0");
        Button sub3_2 = new Button();
        sub3_2.setType("location_select");
        sub3_2.setName("发送位置");
        sub3_2.setKey("rselfmenu_2_0");
        Button group = new Button();
        group.setName("更多功能");
        List<Button> grouplist=new ArrayList<>();
        grouplist.add(sub3_1);
        grouplist.add(sub3_2);
        group.setSub_button(grouplist);

        MenuButtons btn1 = new MenuButtons();
        btn1.setButton(new Button[]{sub1, sub2, group});

        MenuAPI.menuCreate(token.getAccess_token(), btn1);
    }

    @Scheduled(fixedRate= 1000*60*90, initialDelay = 2000)//项目启动2秒中之后执行一次，然后每90min执行一次，单位都为ms
    public void getToken(){
        token = TokenAPI.token(appId,appSecret);
        logger.info("获取Access_token:" + token.getAccess_token());
    }

    @GetMapping("/sign")
    public String Sign(String signature,String timestamp,String nonce,String echostr){
        System.out.println("=======开始请求校验======");
        // 微信加密签名.
        System.out.println("signature====" + signature);
        // 时间戳.
        System.out.println("timestamp====" + timestamp);
        // 随机数.
        System.out.println("nonce====" + nonce);
        // 随机字符串.
        System.out.println("echostr====" + echostr);
        // 请求校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败.
        if (SignUtil.checkSignature(signature, timestamp, nonce)) {
            System.out.println("=======请求校验成功======" + echostr);
           return echostr;
        }
        System.out.println("=======请求校验失败======" + echostr);
        return null;
    }

    @GetMapping("/message")
    public void message(){
//根据openId 群发
//文本群发
        MassTextMessage textMessage2 = new MassTextMessage("openId 列表群发文本消息");
        textMessage2.setTouser(new HashSet<String>());
        textMessage2.getTouser().add("openid_1");
        textMessage2.getTouser().add("openid_2");
        MessageAPI.messageMassSend(token.getAccess_token(),textMessage2);
    }

    /**
     * 网页授权登录
     * @param response
     * @throws IOException
     */
    @RequestMapping("/login")
    public void wxlogin(HttpServletResponse response)throws IOException {
        // 第一步：用户同意授权，获取code
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appId +
                "&redirect_uri=" + springUrl+"%2Fweixin%2Fwxcallback" +
                "&response_type=code" +
                "&scope=snsapi_userinfo" +
                "&state=STATE#wechat_redirect";
        logger.info("login in"+url);
        response.sendRedirect(url);
    }
    @RequestMapping("/wxcallback")
    public void wxcallback(HttpServletResponse response,String code) throws IOException {

        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+appId+"&secret="+appSecret+"&code="+code+"&grant_type=authorization_code";
        JSONObject userInfoJson = HttpClientUtils.doGet(url);
//                "access_token":"ACCESS_TOKEN",
//                "expires_in":7200,
//                "refresh_token":"REFRESH_TOKEN",
//                "openid":"OPENID",
        System.out.println("UserInfo:" + userInfoJson);
            response.sendRedirect(vueUrl+"?openid="+userInfoJson.getString("openid")+"&access_token="+userInfoJson.getString("access_token"));
        }


    @GetMapping("/getinfo")
    public User getInfo(String access_token,String openid) throws IOException{
        if (openid==null||access_token.equals(""))return null;
        List<User> users= userRepository.findAll();
        boolean find=false;
        for (User item:users){
            if(item.getOpenid().equals(openid)) {
                return item;

            }
        }
        String url="https://api.weixin.qq.com/sns/userinfo?access_token="+access_token+"&openid="+openid+"&lang=zh_CN";
        JSONObject userinfo=HttpClientUtils.doGet(url);
        if (userinfo==null)return null;
        User user=new User("",userinfo.getString("openid"),userinfo.getString("nickname"),"",userinfo.getString("city"),userinfo.getString("country"),userinfo.getString("headimgurl"),userinfo.getString("language"),userinfo.getString("province"),userinfo.getInteger("sex"));
        userRepository.save(user);
        return user;
    }

}
