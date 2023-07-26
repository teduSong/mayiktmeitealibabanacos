package com.mayikt.loadbalancer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 蚂蚁课堂创始人-余胜军QQ644064779
 * @title: RotationLoadBalancer
 * @description: 每特教育独创第五期互联网架构课程
 * @date 2020/1/722:08
 */
@Component
public class WeightLoadBalancer implements LoadBalancer {
    /**
     * 从0开始计数
     */
    @Autowired
    private DiscoveryClient discoveryClient;
    private AtomicInteger countAtomicInteger = new AtomicInteger(0);//使用原子类   保证线程安全性

    @Override
    public ServiceInstance getInstances(String serviceId) {
        List<ServiceInstance> instances =
                discoveryClient.getInstances(serviceId);
        //2.判断是否null
        if (instances==null||instances.size()==0){
            return null;
        }
        ArrayList<ServiceInstance> newInstance = new ArrayList<>();
        instances.forEach((service)->{
            Double weight = Double.parseDouble(service.getMetadata().get("nacos.weight"));
            for (int i=0;i<weight;i++){
                    newInstance.add(service);
                }
        });
        int index = countAtomicInteger.incrementAndGet() % newInstance.size();
        return newInstance.get(index);
    }
}
