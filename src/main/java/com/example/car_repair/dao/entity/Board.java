package com.example.car_repair.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class Board extends Model<Board> {


    // 主键

    @TableId
    int id;

    // 公告的内容
    String context;

    // 标题
    String title;

    // 发布时间
    Timestamp time;

    // 删除标志：1已删除，0未删除
    @TableField(value = "deleteFlag")
    Integer deleteFlag = 0;
}
