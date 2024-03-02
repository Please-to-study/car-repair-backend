package com.example.car_repair.controller;

import com.example.car_repair.dao.entity.Board;
import com.example.car_repair.dao.entity.Maintenance;
import com.example.car_repair.dto.UserDto;
import com.example.car_repair.service.IBoardService;
import com.example.car_repair.service.IUserService;
import com.example.car_repair.util.JSONResult;
import com.example.car_repair.util.RegexStr;
import com.example.car_repair.util.Result;
import com.example.car_repair.vo.BoardVo;
import com.example.car_repair.vo.MaintenanceVo;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/board")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:8080"})
public class BoardController {

    private final IBoardService boardService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public JSONResult add(Board board) {

        Result result = boardService.add(board);
        if (!result.isSuccess()) return JSONResult.errorMsg(result.getMsg());

        return JSONResult.ok();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public JSONResult delete(Integer id) {

        Result result = boardService.delete(id);
        if (!result.isSuccess()) return JSONResult.errorMsg(result.getMsg());

        return JSONResult.ok();
    }


    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public JSONResult getAll() {

        Result result = boardService.getAll();
        if (!result.isSuccess()) return JSONResult.errorMsg(result.getMsg());

        // 装换为Vo来返回
        List<Board> list = (List<Board>) result.getData();

        List<BoardVo> res = new ArrayList<>();
        for (Board board : list) {
            res.add(new BoardVo(board));
        }

        return JSONResult.ok(res);
    }
}
