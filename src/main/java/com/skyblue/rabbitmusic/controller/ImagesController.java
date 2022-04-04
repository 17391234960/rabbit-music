package com.skyblue.rabbitmusic.controller;

import com.alibaba.fastjson.JSON;
import com.skyblue.rabbitmusic.dto.ImagePicDto;
import com.skyblue.rabbitmusic.service.ImagePicService;
import com.skyblue.rabbitmusic.vo.Result;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/images")
@AllArgsConstructor
public class ImagesController {
    private final Logger log = LoggerFactory.getLogger(ImagesController.class);


    private final ImagePicService imagePicService;

    /**
     * 获取所有数据库地址
     * @return 所有图片地址
     */
    @GetMapping("/getAllImage")
    public Result<List<ImagePicDto>> getAllImage() {
        return Result.ok(imagePicService.listAll());
    }

    /**
     * 获取随机图片地址 带参数
     * @return 前缀参数 url
     */
    @GetMapping("/getImage")
    public Result<String> getImage() {
        String img = imagePicService.getImageUrl();
        Map<String,Object> parse =  JSON.parseObject(img);
        String pic = (String) parse.get("pic");
        return Result.ok(pic);
    }

    /**
     * 获取随机图片地址
     * @return  image url
     */
    @GetMapping("/getImageUrl")
    public String getImageUrl() {
        String img = imagePicService.getImageUrl();
        Map<String,Object> parse =  JSON.parseObject(img);
        return (String) parse.get("pic");
    }


}
