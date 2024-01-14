package com.example.car_repair.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.car_repair.dao.entity.Maintenance;
import com.example.car_repair.dao.mapper.MaintenanceMapper;
import com.example.car_repair.service.IMaintenanceService;
import com.example.car_repair.util.Result;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class MaintenanceServiceImpl extends ServiceImpl<MaintenanceMapper, Maintenance> implements IMaintenanceService {

    @Override
    public Result add(Maintenance maintenance) {
        Result result = getMaintenanceByMaintenanceId(maintenance.getMaintenanceId());
        if (result.isSuccess()) return Result.errorMsg("维修员已存在，请更换用户名");

        String md5str = DigestUtils.md5DigestAsHex(maintenance.getPassword().getBytes());
        maintenance.setPassword(md5str);
        boolean ok = save(maintenance);
        if (!ok) return Result.errorMsg("新建维修员出错");

        return Result.ok("新建维修员成功");
    }

    @Override
    public Result update(Maintenance maintenance) {
        QueryWrapper<Maintenance> wrapper = new QueryWrapper<>();
        wrapper.eq("maintenanceId", maintenance.getMaintenanceId()).last("limit 1");

        Maintenance old = getOne(wrapper);
        if (old == null) return Result.errorMsg("维修人员不存在");

        Maintenance newMaintenance = new Maintenance(old);

        boolean ok = update(newMaintenance, wrapper);
        if (!ok) return Result.errorMsg("修改维修员信息失败");

        return Result.ok("修改维修员信息成功");
    }

    @Override
    public Result getMaintenanceByMaintenanceId(String maintenanceId) {
        QueryWrapper<Maintenance> wrapper = new QueryWrapper<>();
        wrapper.eq("maintenanceId", maintenanceId).last("limit 1");
        Maintenance maintenance = getOne(wrapper);
        if (maintenance == null) return Result.errorMsg("没有找到维修员");

        return Result.ok(maintenance);
    }

    @Override
    public Result delete(String maintenanceId) {
        Result result = getMaintenanceByMaintenanceId(maintenanceId);
        if (!result.isSuccess()) return Result.errorMsg("该维修员不存在");

        Maintenance maintenance = (Maintenance) result.getData();
        maintenance.setDeleteFlag(1);

        QueryWrapper<Maintenance> wrapper = new QueryWrapper<>();
        wrapper.eq("maintenanceId", maintenanceId).last("limit 1");
        boolean ok = update(maintenance, wrapper);
        if (!ok) return Result.errorMsg("删除维修员信息失败");

        return Result.ok("删除维修员信息成功");
    }
}
