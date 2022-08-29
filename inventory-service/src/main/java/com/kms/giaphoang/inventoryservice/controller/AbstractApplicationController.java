package com.kms.giaphoang.inventoryservice.controller;

import com.kms.giaphoang.inventoryservice.util.ApplicationMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author : giaphoang
 * @mailto : hoanghuugiap241@gmail.com
 * @created : 8/29/2022, Monday
 * @project: spring-boot-stationery-chain
 **/
public abstract class AbstractApplicationController {
    @Autowired
    protected ApplicationMapper mapper;
}
