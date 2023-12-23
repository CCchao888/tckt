package me.thinkchao.tckt.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Author:chao
 * Date:2023-11-10
 * Description:
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients(basePackages = "me.thinkchao")
public class ServiceOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceOrderApplication.class, args);
    }
}