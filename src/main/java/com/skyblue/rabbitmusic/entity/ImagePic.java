package com.skyblue.rabbitmusic.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class ImagePic extends BaseEntity {

    private String picName;

    private String picUrl;

    private String remark;

}
