package com.example.car_repair.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.car_repair.dao.entity.Board;
import com.example.car_repair.dao.mapper.BoardMapper;
import com.example.car_repair.service.IBoardService;
import com.example.car_repair.util.Result;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BoardServiceImpl extends ServiceImpl<BoardMapper, Board> implements IBoardService {

    @Override
    public Result add(Board board) {
        boolean ok = save(board);
        if (!ok) return Result.errorMsg("创建公告失败");

        return Result.ok("新建公告成功");
    }

    @Override
    public Result delete(Integer id) {
        Result result = getBoardById(id);
        if (!result.isSuccess()) return Result.errorMsg("该公告不存在");

        Board board = (Board) result.getData();
        board.setDeleteFlag(1);

        QueryWrapper<Board> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id).last("limit 1");
        boolean ok = update(board, wrapper);
        if (!ok) return Result.errorMsg("删除公告失败");

        return Result.ok("删除公告成功");
    }

    @Override
    public Result getAll() {
        QueryWrapper<Board> wrapper = new QueryWrapper<>();
        wrapper.eq("deleteFlag", 0); // 未删除
        List<Board> res = this.list(wrapper);
        return Result.ok(res);
    }

    @Override
    public Result getBoardById(Integer id) {
        QueryWrapper<Board> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id).last("limit 1");
        Board board = getOne(wrapper);
        if (board == null) return Result.errorMsg("没有找到公告");

        return Result.ok(board);
    }
}
