package com.example.car_repair.util;

public class RegexStr {
    // 2-16位数字字母下划线组成，
    public static String userIdRegex = "[a-zA-Z0-9_]{2,16}";

    // 6-16位数字字母组成
    public static String passwordRegex = "[a-zA-Z0-9]{6,16}";

    // 手机号正则
    public static String phoneRegex = "^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\\d{8}$";

    public static String mailRegex = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";

}
