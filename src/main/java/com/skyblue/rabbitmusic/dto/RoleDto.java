package com.skyblue.rabbitmusic.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RoleDto  extends BaseDto {

    private String name;

    private String title;
}
