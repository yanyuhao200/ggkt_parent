package com.atguigu.ggkt.vod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: yyh
 * @Date: 2022/07/02 14:42
 */

@SpringBootApplication
@ComponentScan("com.atguigu")//扫描此包下面所有相关都加载 使用swagger  （扫描规则）
public class ServiceVodApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceVodApplication.class,args);
    }
}
