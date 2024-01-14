package com.example.car_repair.controller;


import com.example.car_repair.service.IManagerService;
import com.example.car_repair.util.JSONResult;
import com.example.car_repair.util.Result;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/manager")
public class ManagerController {

    IManagerService managerService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public JSONResult login(String managerId, String password) {
        Result result = managerService.login(managerId, password);
        if (!result.isSuccess()) return JSONResult.errorMsg(result.getMsg());

        return JSONResult.ok();
    }

    @RequestMapping(value = "/assignOrder", method = RequestMethod.POST)
    public JSONResult assignOrder(Integer orderId, String maintenanceId) {
        Result result = managerService.assignOrder(orderId, maintenanceId);
        if (!result.isSuccess()) return JSONResult.errorMsg(result.getMsg());

        return JSONResult.ok();
    }
}
