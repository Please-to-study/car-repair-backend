package com.example.car_repair.dao.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.example.car_repair.dto.UserDto;
import lombok.Data;
import org.springframework.util.DigestUtils;

@Data
public class User extends Model<User> {

    @TableId
    long id;

    String userid;

    String name;

    String mail;

    String others;

    String password;

    String phone;

    public User(UserDto userDto) {
        this.userid = userDto.getUserid();
        this.name = userDto.getName();
        this.mail = userDto.getMail();
        // 密码加密
        this.password = DigestUtils.md5DigestAsHex(userDto.getPassword().getBytes());
        this.others = userDto.getOthers();
        this.phone = userDto.getPhone();
    }

    public User() {

    }
}
