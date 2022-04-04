package com.skyblue.rabbitmusic.dto;

import com.skyblue.rabbitmusic.enums.Gender;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class ImagePicDto extends BaseDto {

    private String picName;

    private String picUrl;

    private String remark;

}
