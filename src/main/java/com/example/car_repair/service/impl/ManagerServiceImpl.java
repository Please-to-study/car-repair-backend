package com.example.car_repair.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.car_repair.dao.entity.Manager;
import com.example.car_repair.dao.entity.Order;
import com.example.car_repair.dao.entity.User;
import com.example.car_repair.dao.mapper.ManagerMapper;
import com.example.car_repair.service.IMaintenanceService;
import com.example.car_repair.service.IManagerService;
import com.example.car_repair.service.IOrderService;
import com.example.car_repair.util.Result;
import lombok.AllArgsConstructor;
import org.springframework.jmx.export.naming.MetadataNamingStrategy;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ManagerServiceImpl extends ServiceImpl<ManagerMapper, Manager> implements IManagerService {

    IMaintenanceService maintenanceService;

    IOrderService orderService;

    @Override
    public Result login(String managerId, String password) {
        Manager manager = getById(managerId);
        if (manager == null) return Result.errorMsg("没有找到对应的管理员");

        // 取消加密
//        String md5Str = DigestUtils.md5DigestAsHex(password.getBytes());

        if (!manager.getPassword().equals(password)) return Result.errorMsg("密码错误");

        return Result.ok("验证成功");
    }

    @Override
    public Result assignOrder(Integer orderId, String phone) {

        Result maintenanceRes = maintenanceService.getMaintenanceByPhone(phone);
        if (!maintenanceRes.isSuccess()) return maintenanceRes;

        Result orderRes = orderService.getOrderById(orderId);
        if (!orderRes.isSuccess()) return orderRes;

        Order order = (Order) orderRes.getData();
        // 分配人员
        order.setMaintenance(phone);

        LocalDateTime dateTime = LocalDateTime.now();
        // 时间
        order.setMaintenanceTime(Timestamp.valueOf(dateTime));
        // 状态
        order.setStatus(1);

        boolean ok = orderService.updateById(order);
        if (!ok) return Result.errorMsg("分配维修员失败");

        return Result.ok("分配维修员成功");
    }

    @Override
    public Result assignStatus(Integer orderId, Integer status) {
        Result result = orderService.getOrderById(orderId);
        if (!result.isSuccess()) return Result.errorMsg("没有找到对应的订单");

        Order order = (Order) result.getData();

        LocalDateTime dateTime = LocalDateTime.now();

        switch (order.getStatus()) {
            case 0:
                if (status == 0) return Result.errorMsg("订单已在待分配维修人员状态");
                if (status == 2) return Result.errorMsg("订单还未分配维修人员，无法直接完成");
                order.setMaintenanceTime(Timestamp.valueOf(dateTime));
                break;
            case 1:
                if (status == 0) return Result.errorMsg("订单已在维修中");
                if (status == 1) return Result.errorMsg("订单已在维修中");
                order.setFinishTime(Timestamp.valueOf(dateTime));
                break;
            case 2:
                return Result.errorMsg("订单已完成，无法修改状态");
        }

        order.setStatus(status);

        boolean ok = orderService.updateById(order);

        if (!ok) return Result.errorMsg("修改订单状态失败");
        return Result.ok("修改订单状态成功");
    }

    @Override
    public Result register(Manager manager) {
        Result res = getManager(manager.getManagerId());
        if(res.isSuccess()) return Result.errorMsg("管理员已存在");

        boolean ok = save(manager);
        if (!ok) return Result.errorMsg("创建管理员失败");

        return Result.ok("创建管理员成功");
    }

    @Override
    public Result getManager(String managerId) {
        QueryWrapper<Manager> wrapper = new QueryWrapper<>();
        wrapper.eq("managerId", managerId).last("limit 1");
        Manager manager = getOne(wrapper, false);
        if (manager == null) return Result.errorMsg("没有找到管理员");

        return Result.ok(manager);
    }
}
