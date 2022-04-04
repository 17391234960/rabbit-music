package com.skyblue.rabbitmusic.service.impl;

import com.skyblue.rabbitmusic.dto.ImagePicDto;
import com.skyblue.rabbitmusic.entity.ImagePic;
import com.skyblue.rabbitmusic.mapper.ImagePicMapper;
import com.skyblue.rabbitmusic.repository.ImagePicRepository;
import com.skyblue.rabbitmusic.service.ImagePicService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ImagePicServiceImpl implements ImagePicService {
    private final Logger log = LoggerFactory.getLogger(ImagePicServiceImpl.class);

    private ImagePicRepository imagePicRepository;

    private ImagePicMapper imagePicMapper;

    /**
     * 查询所有图片接口
     * @return List<ImagePicDto>
     */
    @Override
    public List<ImagePicDto> listAll() {
        return imagePicRepository.findAll()
                .stream().map(imagePicMapper::toDTO).collect(Collectors.toList());
    }

    /**
     * 创建接口
     * @param imagePicDto 创建request
     * @return ImagePicDto
     */
    @Override
    public ImagePicDto create(ImagePicDto imagePicDto) {
        ImagePic user = imagePicMapper.toEntity(imagePicDto);
        return imagePicMapper.toDTO(imagePicRepository.save(user));
    }

}
