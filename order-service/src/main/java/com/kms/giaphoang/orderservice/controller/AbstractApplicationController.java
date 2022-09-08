package com.kms.giaphoang.orderservice.controller;

import com.kms.giaphoang.orderservice.util.ApplicationMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author : giaphoang
 * @mailto : hoanghuugiap241@gmail.com
 * @created : 9/8/2022, Thursday
 * @project: spring-boot-stationery-chain
 **/

public abstract class AbstractApplicationController {
    @Autowired
    protected ApplicationMapper mapper;
}
