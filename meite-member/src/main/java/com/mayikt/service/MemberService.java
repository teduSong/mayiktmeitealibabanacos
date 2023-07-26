package com.mayikt.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 蚂蚁课堂创始人-余胜军QQ644064779
 * @title: MemberService
 * @description: 每特教育独创第五期互联网架构课程
 * @date 2020/1/721:42
 */
@RestController
public class MemberService {
    @Value("${server.port}")
    private String serverPort;

    /**
     * 会员服务提供的接口被订单服务调用
     *
     * @param userId
     * @return
     */
    @GetMapping("/getUser")
    public String getUser(Integer userId) {
        return "每特教育|蚂蚁课堂,端口号为:" + serverPort;
    }
}
