package com.example.car_repair.vo;

import com.example.car_repair.dao.entity.Maintenance;
import lombok.Data;

@Data
public class MaintenanceVo {

    int id;

    String name;

    String phone;

    String age;

    String seniority;

    String major;

    public MaintenanceVo(Maintenance maintenance) {
        this.id = maintenance.getId();
        this.name = maintenance.getName();
        this.phone = maintenance.getPhone();
        this.age = maintenance.getAge();
        this.seniority = maintenance.getSeniority();
        this.major = maintenance.getMajor();
    }
}
