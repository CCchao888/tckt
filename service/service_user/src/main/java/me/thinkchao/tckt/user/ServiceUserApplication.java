package me.thinkchao.tckt.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Author:chao
 * Date:2023-11-10
 * Description:
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("me.thinkchao.tckt.user.mapper")
public class ServiceUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceUserApplication.class, args);
    }

}