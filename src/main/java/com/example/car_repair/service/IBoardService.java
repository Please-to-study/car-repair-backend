package com.example.car_repair.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.car_repair.dao.entity.Board;
import com.example.car_repair.dao.entity.Order;
import com.example.car_repair.util.Result;

public interface IBoardService extends IService<Board> {

    public Result add(Board board);

    public Result delete(Integer id);

    public Result getAll();

    public Result getBoardById(Integer id);

}
