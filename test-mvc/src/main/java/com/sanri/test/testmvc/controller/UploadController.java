package com.sanri.test.testmvc.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
public class UploadController {

    @PostMapping("/upload/single")
    public void upload(@RequestParam("file") MultipartFile file){
        System.out.println(file.getOriginalFilename());
    }

    @PostMapping("/upload/multi")
    public void multiUpload(@RequestParam("file") MultipartFile [] files){
        System.out.println(files.length);
    }
}
