package com.example.car_repair.controller;

import com.example.car_repair.dao.entity.Order;
import com.example.car_repair.service.IOrderService;
import com.example.car_repair.util.JSONResult;
import com.example.car_repair.util.Result;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@AllArgsConstructor
public class OrderController {

    IOrderService orderService;

    @RequestMapping(value = "/booking", method = RequestMethod.POST)
    public JSONResult booking(Order order) {

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


    @RequestMapping(value = "/getOrder", method = RequestMethod.GET)
    public JSONResult getOrder(Integer id) {
        if (id == null) return JSONResult.errorMsg("输入格式不正确");

        Result result = orderService.getOrderById(id);
        if (!result.isSuccess()) return JSONResult.errorMsg(result.getMsg());

        return JSONResult.ok(result.getData());
    }


}
