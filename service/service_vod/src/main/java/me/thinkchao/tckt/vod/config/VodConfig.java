package me.thinkchao.tckt.vod.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * Author:chao
 * Date:2023-10-30
 * Description:
 */

@Configuration
@MapperScan("me.thinkchao.tckt.vod.mapper")
public class VodConfig {
}
