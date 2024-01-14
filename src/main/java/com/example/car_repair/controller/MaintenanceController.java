package com.example.car_repair.controller;

import com.example.car_repair.dao.entity.Maintenance;
import com.example.car_repair.service.IMaintenanceService;
import com.example.car_repair.util.JSONResult;
import com.example.car_repair.util.Result;
import com.example.car_repair.vo.MaintenanceVo;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/maintenance")
public class MaintenanceController {

    IMaintenanceService maintenanceService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public JSONResult add(Maintenance maintenance) {

        Result result = maintenanceService.add(maintenance);
        if (!result.isSuccess()) return JSONResult.errorMsg("添加维修员失败");

        return JSONResult.ok();
    }


    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public JSONResult delete(String maintenanceId) {

        Result result = maintenanceService.delete(maintenanceId);
        if (!result.isSuccess()) return JSONResult.errorMsg("删除维修员失败");

        return JSONResult.ok();
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public JSONResult update(Maintenance maintenance) {

        Result result = maintenanceService.update(maintenance);
        if (!result.isSuccess()) return JSONResult.errorMsg("修改维修人员信息失败");

        return JSONResult.ok();
    }


    @RequestMapping(value = "/getMaintenance", method = RequestMethod.GET)
    public JSONResult getMaintenance(String maintenanceId) {
        if (maintenanceId == null) return JSONResult.errorMsg("没有找到对应的维修员");

        Result result = maintenanceService.getMaintenanceByMaintenanceId(maintenanceId);
        if (!result.isSuccess()) return JSONResult.errorMsg("没有找到对应的维修员");

        Maintenance maintenance = (Maintenance) result.getData();
        if (maintenance.getDeleteFlag() != 0) return JSONResult.errorMsg("该维修员已被删除");

        MaintenanceVo maintenanceVo = new MaintenanceVo(maintenance);

        return JSONResult.ok(maintenanceVo);
    }
}
