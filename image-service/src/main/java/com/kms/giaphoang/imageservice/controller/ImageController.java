package com.kms.giaphoang.imageservice.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

/**
 * @author : giaphoang
 * @mailto : hoanghuugiap241@gmail.com
 * @created : 9/7/2022, Wednesday
 * @project: spring-boot-stationery-chain
 **/
@RestController
@RequestMapping("/api/v1/image")
public class ImageController {
    @Value("${cloudinary.cloud_name}")
    private String cloudName;
    @Value("${cloudinary.api_key}")
    private String apiKey;
    @Value("${cloudinary.api_secret}")
    private String apiSecret;

    @PostMapping
    public ResponseEntity<String> uploadImage(@RequestParam("imageFile") MultipartFile file) throws IOException {
        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudName,
                "api_key", apiKey,
                "api_secret", apiSecret,
                "secure", true));
        final Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("use_filename", "true",
                "resource_type", "auto", "folder", "stationery"));
        System.out.println(uploadResult);
        return ResponseEntity.ok(uploadResult.get("url").toString());
    }
}
