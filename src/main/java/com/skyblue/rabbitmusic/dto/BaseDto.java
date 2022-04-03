package com.skyblue.rabbitmusic.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public abstract class BaseDto implements Serializable {
    protected String id;

    protected Date createdTime;

    protected Date updatedTime;
}
