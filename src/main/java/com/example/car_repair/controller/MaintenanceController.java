package com.example.car_repair.controller;

import com.example.car_repair.dao.entity.Maintenance;
import com.example.car_repair.service.IMaintenanceService;
import com.example.car_repair.util.JSONResult;
import com.example.car_repair.util.Result;
import com.example.car_repair.vo.MaintenanceVo;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/maintenance")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:8080"})
public class MaintenanceController {

    IMaintenanceService maintenanceService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public JSONResult add(Maintenance maintenance) {

        Result result = maintenanceService.add(maintenance);
        if (!result.isSuccess()) return JSONResult.errorMsg("添加维修员失败");

        return JSONResult.ok();
    }


    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public JSONResult delete(String phone) {

        Result result = maintenanceService.delete(phone);
        if (!result.isSuccess()) return JSONResult.errorMsg(result.getMsg());

        return JSONResult.ok();
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public JSONResult update(Maintenance maintenance) {

        Result result = maintenanceService.update(maintenance);
        if (!result.isSuccess()) return JSONResult.errorMsg(result.getMsg());

        return JSONResult.ok();
    }


    @RequestMapping(value = "/getMaintenance", method = RequestMethod.GET)
    public JSONResult getMaintenance(String phone) {
        if (phone == null) return JSONResult.errorMsg("请输入正确的手机号码");

        Result result = maintenanceService.getMaintenanceByPhone(phone);
        if (!result.isSuccess()) return JSONResult.errorMsg(result.getMsg());

        Maintenance maintenance = (Maintenance) result.getData();
        if (maintenance.getDeleteFlag() != 0) return JSONResult.errorMsg("该维修员已被删除");

        MaintenanceVo maintenanceVo = new MaintenanceVo(maintenance);

        return JSONResult.ok(maintenanceVo);
    }


    @RequestMapping(value = "/getAllMaintenance", method = RequestMethod.GET)
    public JSONResult getAllMaintenance() {
        Result result = maintenanceService.getAllMaintenance();
        if (!result.isSuccess()) return JSONResult.errorMsg(result.getMsg());

        // 装换为Vo来返回
        List<Maintenance> list = (List<Maintenance>) result.getData();
        List<MaintenanceVo> res = new ArrayList<>();
        for (Maintenance maintenance : list) {
            res.add(new MaintenanceVo(maintenance));
        }

        return JSONResult.ok(res);
    }
}
