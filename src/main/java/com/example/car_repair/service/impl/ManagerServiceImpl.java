package com.example.car_repair.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.car_repair.dao.entity.Manager;
import com.example.car_repair.dao.entity.Order;
import com.example.car_repair.dao.mapper.ManagerMapper;
import com.example.car_repair.service.IMaintenanceService;
import com.example.car_repair.service.IManagerService;
import com.example.car_repair.service.IOrderService;
import com.example.car_repair.util.Result;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
@AllArgsConstructor
public class ManagerServiceImpl extends ServiceImpl<ManagerMapper, Manager> implements IManagerService {

    IMaintenanceService maintenanceService;

    IOrderService orderService;

    @Override
    public Result login(String managerId, String password) {
        Manager manager = getById(managerId);
        if (manager == null) return Result.errorMsg("没有找到对应的管理员");

        String md5Str = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!manager.getPassword().equals(md5Str)) return Result.errorMsg("密码错误");

        return Result.ok("验证成功");
    }

    @Override
    public Result assignOrder(Integer id, String maintenanceId) {

        Result maintenanceRes = maintenanceService.getMaintenanceByMaintenanceId(maintenanceId);
        if (!maintenanceRes.isSuccess()) return maintenanceRes;

        Result orderRes = orderService.getOrderById(id);
        if (!orderRes.isSuccess()) return orderRes;

        Order order = (Order) orderRes.getData();
        order.setMaintenanceId(maintenanceId);

        boolean ok = orderService.updateById(order);
        if (!ok) return Result.errorMsg("分配维修员失败");

        return Result.ok("分配维修员成功");
    }
}
