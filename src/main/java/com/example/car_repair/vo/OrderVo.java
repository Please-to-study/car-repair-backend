package com.example.car_repair.vo;

import com.example.car_repair.dao.entity.Maintenance;
import com.example.car_repair.dao.entity.Order;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Time;
import java.sql.Timestamp;

@Data
public class OrderVo {

    int id;

    // 用户表的ID
    String userid;

    // 维修项目
    String maintenanceProject;

    // 问题描述
    String problem;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT + 8")
    // 下单时间
    Timestamp orderTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT + 8")
    // 预约时间
    Timestamp orderDate;

    // 接单时间
    Timestamp maintenanceTime;

    // 完成时间
    Timestamp finishTime;

    // 维修员
    Maintenance maintenance;

    // 订单状态 0-待分配维修人员，1-正在维修， 2-维修完成
    int status = 0;

    // 评论
    String comment;

    public OrderVo(Order order) {
        this.id = order.getId();
        this.comment = order.getComment();
        this.orderDate = order.getOrderDate();
        this.finishTime = order.getFinishTime();
        this.orderTime = order.getOrderTime();
        this.problem = order.getProblem();
        this.status = order.getStatus();
        this.userid = order.getUserid();
        this.maintenanceProject = order.getMaintenanceProject();
    }
}
