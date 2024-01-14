package com.example.car_repair.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Time;
import java.sql.Timestamp;

@TableName("`order`")
@Data
public class Order extends Model<Order> {

    @TableId
    int id;

    // 用户表的ID
    String userid;

    @TableField(value = "maintenanceProject")
    // 维修项目
    String maintenanceProject;

    // 问题描述
    String problem;

    @TableField(value = "orderTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT + 8")
    // 下单时间
    Timestamp orderTime;

    @TableField(value = "orderDate")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT + 8")
    // 预约时间
    Timestamp orderDate;

    @TableField(value = "maintenanceTime")
    // 接单时间
    Time maintenanceTime;

    @TableField(value = "finishTime")
    // 完成时间
    Time finishTime;

    // 维修员
    String maintenance;

    // 订单状态 0-待分配维修人员，1-正在维修， 2-维修完成
    int status = 0;

    // 评论
    String comment;

}
