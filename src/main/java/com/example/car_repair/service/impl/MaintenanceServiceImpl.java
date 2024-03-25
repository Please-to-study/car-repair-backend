package com.example.car_repair.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.car_repair.dao.entity.Maintenance;
import com.example.car_repair.dao.mapper.MaintenanceMapper;
import com.example.car_repair.service.IMaintenanceService;
import com.example.car_repair.util.Result;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaintenanceServiceImpl extends ServiceImpl<MaintenanceMapper, Maintenance> implements IMaintenanceService {

    @Override
    public Result add(Maintenance maintenance) {
        Result result = getMaintenanceByPhone(maintenance.getPhone());
        if (result.isSuccess()) return Result.errorMsg("电话号码已被注册");

        boolean ok = save(maintenance);
        if (!ok) return Result.errorMsg("新建维修员出错");

        return Result.ok("新建维修员成功");
    }

    @Override
    public Result update(Maintenance maintenance) {
        QueryWrapper<Maintenance> wrapper = new QueryWrapper<>();
        wrapper.eq("phone", maintenance.getPhone()).last("limit 1");

        Maintenance old = getOne(wrapper);
        if (old == null) return Result.errorMsg("维修人员不存在");

//        Maintenance newMaintenance = old.copy(maintenance);
        Maintenance newMaintenance = maintenance;

        boolean ok = update(newMaintenance, wrapper);
        if (!ok) return Result.errorMsg("修改维修员信息失败");

        return Result.ok("修改维修员信息成功");
    }

    @Override
    public Result getMaintenanceByPhone(String phone) {
        QueryWrapper<Maintenance> wrapper = new QueryWrapper<>();
        wrapper.eq("phone", phone).last("limit 1");
        Maintenance maintenance = getOne(wrapper);
        if (maintenance == null) return Result.errorMsg("没有找到维修员");

        return Result.ok(maintenance);
    }

    @Override
    public Result delete(String phone) {
        Result result = getMaintenanceByPhone(phone);
        if (!result.isSuccess()) return Result.errorMsg("该维修员不存在");

        Maintenance maintenance = (Maintenance) result.getData();
        maintenance.setDeleteFlag(1);

        QueryWrapper<Maintenance> wrapper = new QueryWrapper<>();
        wrapper.eq("phone", phone).last("limit 1");
        boolean ok = update(maintenance, wrapper);
        if (!ok) return Result.errorMsg("删除维修员信息失败");

        return Result.ok("删除维修员信息成功");
    }

    @Override
    public Result getAllMaintenance() {
        QueryWrapper<Maintenance> wrapper = new QueryWrapper<>();
        wrapper.eq("deleteFlag", 0); // 未删除
        List<Maintenance> res = this.list(wrapper);
        return Result.ok(res);
    }
}
