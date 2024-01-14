package com.example.car_repair.vo;

import com.example.car_repair.dao.entity.Maintenance;
import lombok.Data;

@Data
public class MaintenanceVo {

    int id;

    String maintenanceId;

    String name;

    String phone;

    public MaintenanceVo(Maintenance maintenance) {
        this.id = maintenance.getId();
        this.name = maintenance.getName();
        this.phone = maintenance.getPhone();
        this.maintenanceId = maintenance.getMaintenanceId();
    }
}
