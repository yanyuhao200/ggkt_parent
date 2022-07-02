package com.atguigu.ggkt.vod.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: yyh
 * @Date: 2022/07/02 14:43
 */

@Configuration
@MapperScan("com.atguigu.ggkt.vod.mapper")
public class VodConfig {
}
