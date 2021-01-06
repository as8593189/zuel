package com.zuel.passport.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zuel.common.vo.ZuelResult;
import com.zuel.exception.ServiceException;
import com.zuel.passport.service.PassportService;
import com.zuel.pojo.TbUser;

/*
 * @author:汪思超
 * @class:用户登录服务视图控制器
 * @date:2021.1.6
 * */

@RestController
public class PassportController {

	@Autowired
    private PassportService passportService;


    @PostMapping("/user/logout/{token}")
    @CrossOrigin(allowCredentials = "true")
    public ZuelResult logout(@PathVariable("token") String token, HttpSession session){
        session.invalidate();
        return ZuelResult.ok();
    }

    @GetMapping("/user/token/{token}")
    @CrossOrigin(allowCredentials = "true")
    public ZuelResult checkUserLogin(@PathVariable("token") String token, HttpSession session){
        Object obj = session.getAttribute("egoLoginUser");
        if(null != obj){ 
            TbUser user = (TbUser) obj;
            return ZuelResult.ok(user);
        }
        return ZuelResult.error("用户未登录");
    }


    @PostMapping("/user/login")
    public ZuelResult login(String username, String password, HttpSession session){
        ZuelResult result = this.passportService.login(username, password, session);
        return result;
    }


    @PostMapping("/user/register")
    public ZuelResult register(TbUser user, String pwdRepeat){
        int usernameLength = user.getUsername().trim().length();
        if(usernameLength < 6 || usernameLength > 12){
            return ZuelResult.error("用户名长度在6~12之间");
        }
        String password = user.getPassword();
        if(!password.matches("^[a-zA-Z0-9]{6,12}$")){
            return ZuelResult.error("密码长度在6~12之间，且只能由数字和字母组成");
        }
        if(!password.equals(pwdRepeat)){
            return ZuelResult.error("两次密码不一致，请检查");
        }
        String email = user.getEmail();
        if(!email.matches("^[a-z\\d]+(\\.[a-z\\d]+)*@([\\da-z])+(\\.[a-z]+)+$")){
            return ZuelResult.error("邮箱格式错误，请检查");
        }
        String phone = user.getPhone();
        if(!phone.matches("^1\\d{10}$")){
            return ZuelResult.error("手机号码格式错误，请检查");
        }
        ZuelResult result = null;
        try {
            result = this.passportService.register(user);
        } catch (ServiceException e) {
            e.printStackTrace();
            result = ZuelResult.error("服务器忙，请稍后重试");
        }

        return result;
    }

    @GetMapping("/user/check/{principal}/{type}")
    public ZuelResult check(@PathVariable("principal") String principal, @PathVariable("type") int type){
        ZuelResult result = passportService.check(principal, type);
        return result;
    }
}
