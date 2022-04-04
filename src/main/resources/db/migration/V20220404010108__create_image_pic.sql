CREATE TABLE image_pic
(
    id VARCHAR(32) NOT NULL
        PRIMARY KEY COMMENT '图片id',
    pic_name VARCHAR(64) NOT NULL COMMENT '图片名称',
    pic_url VARCHAR(255) NOT NULL COMMENT '图片URL',
    remark VARCHAR(64) NULL COMMENT '备注',
    created_time datetime(6) NOT NULL COMMENT '创建时间',
    updated_time datetime(6) NOT NULL COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT '图片链接表';