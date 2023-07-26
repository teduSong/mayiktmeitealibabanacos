package com.mayikt.loadbalancer;

import org.springframework.cloud.client.ServiceInstance;

/**
 * @author 蚂蚁课堂创始人-余胜军QQ644064779
 * @title: LoadBalancer
 * @description: 每特教育独创第五期互联网架构课程
 * @date 2020/1/722:07
 */
public interface LoadBalancer {
    /**
     * 从注册中心集群列表中获取单个地址
     * @param serviceId
     * @return
     */
    ServiceInstance getInstances(String serviceId);

}
