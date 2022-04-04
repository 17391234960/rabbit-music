package com.skyblue.rabbitmusic.service;

import com.skyblue.rabbitmusic.dto.ImagePicDto;

import java.util.List;

public interface ImagePicService {

    /**
     * 查询所有图片接口
     * @return List<UserDto>
     */
    List<ImagePicDto> listAll();

    /**
     * 创建图片接口
     * @param imagePicDto 创建用户request
     * @return UserDto
     */
    ImagePicDto create(ImagePicDto imagePicDto);

}
