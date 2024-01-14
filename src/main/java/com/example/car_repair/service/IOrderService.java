package com.example.car_repair.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.car_repair.dao.entity.Order;
import com.example.car_repair.util.Result;

public interface IOrderService extends IService<Order> {

    public Result booking(Order order);

    public Result getOrderById(Integer id);

    public Result comment(Order order);

    // 获取所有的订单
    public Result getAllOrder(String userid);

    // 获取所有未分配维修人员的订单
    public Result getNoAssign();
}
