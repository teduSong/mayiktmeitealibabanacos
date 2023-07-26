package com.mayikt.service;


import com.mayikt.loadbalancer.RandomLoadBalancer;
import com.mayikt.loadbalancer.RoundLoadBalancer;
import com.mayikt.loadbalancer.WeightLoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author 蚂蚁课堂创始人-余胜军QQ644064779
 * @title: OrderService
 * @description: 每特教育独创第五期互联网架构课程
 * @date 2020/1/721:47
 */
@RestController
@Slf4j
public class OrderService {
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private RandomLoadBalancer randomLoadBalancer;
    @Autowired
    private WeightLoadBalancer weightLoadBalancer;

    /**
     * 订单服务调用到我们的会员服务接口
     *localhost:8090/orderToMember
     * @return
     */
    @RequestMapping("/orderToMember")
    public String orderToMember(){
        // 1.根据服务名称从 注册中心获取集群列表地址
       /* List<ServiceInstance> instances =
                discoveryClient.getInstances("meitemayikt-member");
        // 2.列表任意选择一个 实现本地rpc调用 rest 采用我们负载均衡的算法
        ServiceInstance srviceInstance = loadBalancer.getSingleAddres(instances);*/

        /*//调用我们需要的本地负载均衡算法  随机算法、轮询算法
        ServiceInstance serviceInstance = randomLoadBalancer.getInstances("meitemayikt-member");
        String memberUrl = "http://" +serviceInstance.getHost()+":"+serviceInstance.getPort()+"/"+"getUser";
        return "订单调用会员返回结果:" + restTemplate.getForObject(memberUrl,String.class);*/

        //获取该服务集群地址   遍历每一个地址
        /*List<ServiceInstance> instances =
                discoveryClient.getInstances("meitemayikt-member");
        for(int i=0;i< instances.size();i++){
            try{
                ServiceInstance serviceInstance = instances.get(i);
                String memberUrl = "http://" +serviceInstance.getHost()+":"+serviceInstance.getPort()+"/"+"getUser";
                return "订单调用会员返回结果:" + restTemplate.getForObject(memberUrl,String.class);
            }catch (RestClientException e){
                log.error("rpc远程调用发生了故障 开始故障转移 切换下一个地址调用 e:{}",e);
            }
        }
        return "fail";*/

        //权重算法
        ServiceInstance serviceInstance = weightLoadBalancer.getInstances("meitemayikt-member");
        String memberUrl = "http://" +serviceInstance.getHost()+":"+serviceInstance.getPort()+"/"+"getUser";
        return "订单调用会员返回结果:" + restTemplate.getForObject(memberUrl,String.class);
    }
}
