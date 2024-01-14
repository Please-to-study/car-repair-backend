package com.example.car_repair.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

@Data
public class Maintenance extends Model<Maintenance> {

    int id;

    @TableField(value = "maintenanceId")
    String maintenanceId;

    String name;

    String phone;

    String password;

    // 删除标志：1已删除，0未删除
    @TableField(value = "deleteFlag")
    Integer deleteFlag = 0;

    // 无参构造
    public Maintenance() {

    }

    public Maintenance(Maintenance maintenance) {
        this.name = maintenance.getName();
        this.phone = maintenance.getPhone();
        this.deleteFlag = maintenance.getDeleteFlag();
    }
}
