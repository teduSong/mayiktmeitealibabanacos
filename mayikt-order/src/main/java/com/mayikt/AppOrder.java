package com.mayikt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author 蚂蚁课堂创始人-余胜军QQ644064779
 * @title: AppOrder
 * @description: 每特教育独创第五期互联网架构课程
 * @date 2020/1/721:50
 */
@SpringBootApplication
public class AppOrder {
    public static void main(String[] args) {
        SpringApplication.run(AppOrder.class);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
