package com.skyblue.rabbitmusic.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Role extends BaseEntity {

    private String name;

    private String title;

}
