package com.skyblue.rabbitmusic.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RoleVo extends BaseVo {
    private String id;

    private String name;

    private String title;
}
