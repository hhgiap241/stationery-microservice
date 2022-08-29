package com.kms.giaphoang.productservice.controller;

import com.kms.giaphoang.productservice.utils.ApplicationMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author : giaphoang
 * @mailto : hoanghuugiap241@gmail.com
 * @created : 8/26/2022, Friday
 * @project: product-service
 **/
public abstract class AbstractApplicationController {
    @Autowired
    protected ApplicationMapper mapper;
}
