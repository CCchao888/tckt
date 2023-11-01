package me.thinkchao.tckt.vod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Author:chao
 * Date:2023-10-30
 * Description:
 */
@SpringBootApplication
@ComponentScan("me.thinkchao")
public class ServiceVodApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceVodApplication.class, args);
    }
}
