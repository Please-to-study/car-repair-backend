package com.example.car_repair.controller;


import com.example.car_repair.dao.entity.Manager;
import com.example.car_repair.dto.UserDto;
import com.example.car_repair.service.IManagerService;
import com.example.car_repair.util.JSONResult;
import com.example.car_repair.util.RegexStr;
import com.example.car_repair.util.Result;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.print.attribute.standard.MediaName;

@RestController
@AllArgsConstructor
@RequestMapping("/manager")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:8080"})
public class ManagerController {

    IManagerService managerService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public JSONResult login(String managerId, String password) {
        Result result = managerService.login(managerId, password);
        if (!result.isSuccess()) return JSONResult.errorMsg(result.getMsg());

        return JSONResult.ok();
    }

    @RequestMapping(value = "/assignOrder", method = RequestMethod.POST)
    public JSONResult assignOrder(Integer orderId, String phone) {
        Result result = managerService.assignOrder(orderId, phone);
        if (!result.isSuccess()) return JSONResult.errorMsg(result.getMsg());

        return JSONResult.ok();
    }

    @RequestMapping(value = "/assignStatus", method = RequestMethod.POST)
    public JSONResult assignStatus(Integer orderId, Integer status) {

        Result result = managerService.assignStatus(orderId, status);
        if (!result.isSuccess()) return JSONResult.errorMsg(result.getMsg());

        return JSONResult.ok();
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public JSONResult register(Manager manager) {
        if(manager.getManagerId() == null ||  !manager.getManagerId().matches(RegexStr.userIdRegex))
            return JSONResult.errorMsg("用户名不符合要求");

        if(manager.getPassword() == null ||  !manager.getPassword().matches(RegexStr.passwordRegex))
            return JSONResult.errorMsg("密码不符合要求");

        Result result = managerService.register(manager);
        if (!result.isSuccess()) return JSONResult.errorMsg(result.getMsg());

        return JSONResult.ok();
    }
}
