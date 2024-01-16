package com.example.car_repair.controller;

import com.example.car_repair.dao.entity.Order;
import com.example.car_repair.service.IOrderService;
import com.example.car_repair.util.JSONResult;
import com.example.car_repair.util.Result;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@AllArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:8080"})
public class OrderController {

    IOrderService orderService;

    @RequestMapping(value = "/booking", method = RequestMethod.POST)
    public JSONResult booking(Order order) {
        if (order.getUserid() == null)
            return JSONResult.errorMsg("用户id不能为空");
        if (order.getProblem() == null)
            return JSONResult.errorMsg("问题描述不能为空");
        if (order.getMaintenanceProject() == null)
            return JSONResult.errorMsg("维修类别不能为空");
        if (order.getOrderDate() == null)
            return JSONResult.errorMsg("预约时间不能为空");

        Result result = orderService.booking(order);
        if (!result.isSuccess()) return JSONResult.errorMsg(result.getMsg());

        return JSONResult.ok();
    }


    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public JSONResult comment(Order order) {
        if (order.getId() == 0 || order.getComment() == null || order.getUserid() == null)
            return JSONResult.errorMsg("输入格式不正确");

        Result result = orderService.comment(order);
        if (!result.isSuccess()) return JSONResult.errorMsg(result.getMsg());

        return JSONResult.ok();
    }

    // 根据订单ID获取单个订单
    @RequestMapping(value = "/getOrder", method = RequestMethod.GET)
    public JSONResult getOrder(Integer id) {
        if (id == null) return JSONResult.errorMsg("输入格式不正确");

        Result result = orderService.getOrderById(id);
        if (!result.isSuccess()) return JSONResult.errorMsg(result.getMsg());

        return JSONResult.ok(result.getData());
    }

    // 获取所有的订单
    @RequestMapping(value = "/getAllOrder", method = RequestMethod.GET)
    public JSONResult getAllOrder(String userid) {

//        if (managerId == null) return JSONResult.errorMsg("只有管理员才能使用该功能");

        Result result = orderService.getAllOrder(userid);
        if (!result.isSuccess()) return JSONResult.errorMsg(result.getMsg());

        return JSONResult.ok(result.getData());
    }

    // 获取所有未分配维修人员的订单
    @RequestMapping(value = "/getNoAssign", method = RequestMethod.GET)
    public JSONResult getNoAssign(String managerId) {

//        if (managerId == null) return JSONResult.errorMsg("只有管理员才能使用该功能");

        Result result = orderService.getNoAssign();
        if (!result.isSuccess()) return JSONResult.errorMsg(result.getMsg());

        return JSONResult.ok(result.getData());
    }

    @RequestMapping(value = "/getAllFinish", method = RequestMethod.GET)
    public JSONResult getAllFinish(String managerId) {

//        if (managerId == null) return JSONResult.errorMsg("只有管理员才能使用该功能");

        Result result = orderService.getAllFinish();
        if (!result.isSuccess()) return JSONResult.errorMsg(result.getMsg());

        return JSONResult.ok(result.getData());
    }

    @RequestMapping(value = "/getAllNoFinish", method = RequestMethod.GET)
    public JSONResult getAllNoFinish(String managerId) {


//        if (managerId == null) return JSONResult.errorMsg("只有管理员才能使用该功能");

        Result result = orderService.getAllNoFinish();
        if (!result.isSuccess()) return JSONResult.errorMsg(result.getMsg());

        return JSONResult.ok(result.getData());
    }
}
