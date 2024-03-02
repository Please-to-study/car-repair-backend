package com.example.car_repair.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.example.car_repair.dao.entity.Board;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class BoardVo extends Model<BoardVo> {


    // 主键

    @TableId
    int id;

    // 公告的内容
    String context;

    // 标题
    String title;

    // 发布时间
    Timestamp time;

    public BoardVo(Board board) {
        this.id = board.getId();
        this.time = board.getTime();
        this.title = board.getTitle();
        this.context = board.getContext();
    }

}
