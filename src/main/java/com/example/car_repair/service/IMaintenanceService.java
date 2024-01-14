package com.example.car_repair.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.car_repair.dao.entity.Maintenance;
import com.example.car_repair.dto.MaintenanceDto;
import com.example.car_repair.util.Result;

public interface IMaintenanceService extends IService<Maintenance> {

    public Result add (Maintenance maintenance);

    public Result update(Maintenance maintenance);

    public Result getMaintenanceByMaintenanceId(String id);

    public Result delete(String id);
}
