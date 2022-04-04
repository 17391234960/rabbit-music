package com.skyblue.rabbitmusic.dto;

import com.skyblue.rabbitmusic.enums.Gender;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserDto extends BaseDto {

    private String username;

    private String nickname;

    private List<RoleDto> roles;

    private Gender gender;

    private Boolean locked;

    private Boolean enabled;
    
    private String lastLoginIp;

    private Date lastLoginTime;

}
