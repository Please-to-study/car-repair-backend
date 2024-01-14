package com.example.car_repair.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.car_repair.dao.entity.Manager;
import com.example.car_repair.util.Result;

public interface IManagerService extends IService<Manager> {

    public Result login(String managerId, String password);

    public Result assignOrder(Integer orderId, String phone);

    public Result assignStatus(Integer orderId, Integer status);
}
