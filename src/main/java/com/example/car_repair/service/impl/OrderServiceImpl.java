package com.example.car_repair.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.car_repair.dao.entity.Order;
import com.example.car_repair.dao.mapper.OrderMapper;
import com.example.car_repair.service.IOrderService;
import com.example.car_repair.service.IUserService;
import com.example.car_repair.util.Result;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    IUserService userService;

    @Override
    public Result booking(Order order) {
        Result result = userService.getUserByUserId(order.getUserid());
        if (!result.isSuccess()) return Result.errorMsg("没有找到对应用户");

        Order newOrder = new Order();
        newOrder.setUserid(order.getUserid());
        newOrder.setProblem(order.getProblem());
        newOrder.setMaintenanceProject(order.getMaintenanceProject());
        newOrder.setOrderDate(order.getOrderDate());

        LocalDateTime dateTime = LocalDateTime.now();
        newOrder.setOrderTime(Timestamp.valueOf(dateTime));

        boolean ok = save(newOrder);
        if (!ok) return Result.errorMsg("下单失败");

        return Result.ok("下单成功");
    }

    @Override
    public Result getOrderById(Integer id) {
        Order order = getById(id);
        if (order == null) return Result.errorMsg("没有找到对应订单");

        return Result.ok(order);
    }

    @Override
    public Result comment(Order order) {
        Order old = getById(order.getId());
        if (old == null) return Result.errorMsg("没有找到对应订单");

        old.setComment(order.getComment());

        boolean ok = updateById(old);
        if (!ok) return Result.errorMsg("评论失败");

        return Result.ok("评论成功");
    }

    @Override
    public Result getAllOrder(String userid) {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("userid", userid);
        List<Order> res = this.list(wrapper);
        return Result.ok(res);
    }

    @Override
    public Result getNoAssign() {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.isNull("maintenance");
        List<Order> res = this.list(wrapper);
        return Result.ok(res);
    }
}
