package com.mayikt.loadbalancer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 蚂蚁课堂创始人-余胜军QQ644064779
 * @title: RotationLoadBalancer
 * @description: 每特教育独创第五期互联网架构课程
 * @date 2020/1/722:08
 */
@Component
public class RandomLoadBalancer implements LoadBalancer {
    /**
     * 从0开始计数
     */
    @Autowired
    private DiscoveryClient discoveryClient;
    @Override
    public ServiceInstance getInstances(String serviceId) {
        List<ServiceInstance> instances =
                discoveryClient.getInstances(serviceId);
        //2.判断是否null
        if (instances==null||instances.size()==0){
            return null;
        }
        //生成随机 范围
        Random random = new Random();
        int index = random.nextInt(instances.size());//随机数从0开始  小于设定的数值
        return  instances.get(index);
    }
}
