package com.ly;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * @ClassName test
 * @Description TODO 生成加密码
 * @Author 赖昱
 * @Date 2023/5/17 - 5:26
 */
public class test {

    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode1 = passwordEncoder.encode("123456");
        String encode2 = passwordEncoder.encode("123456");
        String encode3 = passwordEncoder.encode("123456");
        String encode4 = passwordEncoder.encode("123456");
        System.out.println(encode1);
        System.out.println(encode2);
        System.out.println(encode3);
        System.out.println(encode4);
    }
}
