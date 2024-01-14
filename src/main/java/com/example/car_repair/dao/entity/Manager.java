package com.example.car_repair.dao.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

@Data
public class Manager extends Model<Manager> {

    @TableId(value = "managerId")
    String managerId;

    String password;

}
