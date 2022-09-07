package com.kms.giaphoang.imageservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.PropertySource;

/**
 * @author : giaphoang
 * @mailto : hoanghuugiap241@gmail.com
 * @created : 9/7/2022, Wednesday
 * @project: spring-boot-stationery-chain
 **/
@SpringBootApplication
@EnableEurekaClient
@PropertySource({"classpath:application.yml", "classpath:secret.yml"})
public class ImageServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImageServiceApplication.class, args);
    }

}
