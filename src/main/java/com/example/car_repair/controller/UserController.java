package com.example.car_repair.controller;

import com.example.car_repair.dto.UserDto;
import com.example.car_repair.service.IUserService;
import com.example.car_repair.util.JSONResult;
import com.example.car_repair.util.RegexStr;
import com.example.car_repair.util.Result;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;

@RestController
@AllArgsConstructor
public class UserController {

    private final IUserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public JSONResult register(UserDto userDto) {

        if (!userDto.getUserid().matches(RegexStr.userIdRegex)) {
            return JSONResult.errorMsg("用户名不符合要求");
        }

        if (!userDto.getMail().matches(RegexStr.mailRegex)) {
            return JSONResult.errorMsg("邮箱格式不正确");
        }

        if (!userDto.getPhone().matches(RegexStr.phoneRegex)) {
            return JSONResult.errorMsg("手机号码格式不正确");
        }

        if (!userDto.getPassword().matches(RegexStr.passwordRegex)) {
            return JSONResult.errorMsg("密码格式不正确");
        }

        Result result = userService.register(userDto);
        if (!result.isSuccess()) return JSONResult.errorMsg(result.getMsg());

        return JSONResult.ok();
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public JSONResult login(UserDto userDto) {

        Result result = userService.login(userDto);
        if (!result.isSuccess()) return JSONResult.errorMsg(result.getMsg());

        return JSONResult.ok();
    }

}
