package com.example.car_repair.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.sun.tools.javac.Main;
import lombok.Data;

@Data
public class Maintenance extends Model<Maintenance> {

    int id;

    String name;

    String phone;

    // 年龄
    String age;

    // 删除标志：1已删除，0未删除
    @TableField(value = "deleteFlag")
    Integer deleteFlag = 0;

    // 主修项目
    String major;

    // 工龄
    String seniority;

    // 无参构造
    public Maintenance() {

    }

    public Maintenance copy(Maintenance maintenance) {
        this.name = maintenance.getName();
        this.phone = maintenance.getPhone();
        this.age = maintenance.getAge();
        this.deleteFlag = maintenance.getDeleteFlag();
        return this;
    }
}
