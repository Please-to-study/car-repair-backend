package com.example.car_repair.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.car_repair.dao.entity.User;
import com.example.car_repair.dto.UserDto;
import com.example.car_repair.util.Result;

public interface IUserService extends IService<User> {

    Result register(UserDto userDto);

    Result getUserByUserId(String userid);

    Result login(UserDto userDto);
}
