package com.skyblue.rabbitmusic.mapper;

import com.skyblue.rabbitmusic.dto.ImagePicDto;
import com.skyblue.rabbitmusic.entity.ImagePic;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface ImagePicMapper {

    ImagePicDto toDTO(ImagePic imagePic);

    ImagePic toEntity(ImagePicDto imagePicDto);
}
