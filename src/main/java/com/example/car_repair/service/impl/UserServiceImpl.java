package com.example.car_repair.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.car_repair.dao.entity.User;
import com.example.car_repair.dao.mapper.UserMapper;
import com.example.car_repair.dto.UserDto;
import com.example.car_repair.service.IUserService;
import com.example.car_repair.util.Result;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
@AllArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Override
    public Result register(UserDto userDto) {
        Result result = getUserByUserId(userDto.getUserid());
        if (result.isSuccess()) return Result.errorMsg("用户已存在");

        User user = new User(userDto);
        boolean ok = save(user);
        if (!ok) return Result.errorMsg("创建用户失败");

        return Result.ok("新建用户成功");
    }

    @Override
    public Result getUserByUserId(String userid) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("userid", userid).last("limit 1");
        User user = getOne(wrapper, false);
        if (user == null) return Result.errorMsg("没有找到对应用户");

        return Result.ok(user);
    }

    @Override
    public Result login(UserDto userDto) {
        Result result = getUserByUserId(userDto.getUserid());
        if (!result.isSuccess()) return Result.errorMsg("没有找到对应用户");

        User user = (User) result.getData();
        String md5Str = DigestUtils.md5DigestAsHex(userDto.getPassword().getBytes());
        if (!user.getPassword().equals(md5Str)) return Result.errorMsg("密码错误");

        return Result.ok("验证成功");

    }
}
