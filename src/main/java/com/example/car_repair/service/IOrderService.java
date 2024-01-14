package com.example.car_repair.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.car_repair.dao.entity.Order;
import com.example.car_repair.util.Result;

public interface IOrderService extends IService<Order> {

    public Result booking(Order order);

    public Result getOrderById(Integer id);

    public Result comment(Order order);

}
