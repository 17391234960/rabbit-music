INSERT INTO `rabbit_music`.`user` (`id`, `username`, `nickname`, `password`, `gender`, `locked`, `enabled`, `last_login_ip`, `last_login_time`, `created_time`, `updated_time`) VALUES ('27HckriOAFe94pUKEENMUmGjlum', 'rabbit', '兔子', '$2a$10$5qheyj6tOYH5Lv3YEnP.RO4gJkQV1w1o0uNY3WgA.GHDbYmlSCRxm', 'FEMAILE', 0, 1, NULL, NULL, '2022-04-03 18:09:57.935000', '2022-04-03 18:09:57.935000');
INSERT INTO `rabbit_music`.`user` (`id`, `username`, `nickname`, `password`, `gender`, `locked`, `enabled`, `last_login_ip`, `last_login_time`, `created_time`, `updated_time`) VALUES ('27LCBaYftkMV0YS513mHcw9LCYv', 'test', 'test', '$2a$10$mWzmyqMrdwpxztnwYV3Q0ucMN3JygfHATm6jcscCe0GZ6hrkKp4R2', 'FEMAILE', 0, 1, NULL, NULL, '2022-04-05 00:30:44.625000', '2022-04-05 00:30:44.625000');
INSERT INTO `rabbit_music`.`role` (id, name, title, created_time, updated_time)
VALUES ('1', 'ROLE_ADMIN', '超级管理员', '2022-04-03 18:09:57.935000', '2022-04-03 18:09:57.935000');
INSERT INTO `rabbit_music`.`role` (id, name, title, created_time, updated_time)
VALUES ('2', 'ROLE_USER', '普通用户', '2022-04-03 18:09:57.935000', '2022-04-03 18:09:57.935000');
INSERT INTO `rabbit_music`.`user_role` (user_id, role_id)
VALUES ('27HckriOAFe94pUKEENMUmGjlum', '1');
INSERT INTO `rabbit_music`.`user_role` (user_id, role_id)
VALUES ('27HckriOAFe94pUKEENMUmGjlum', '2');
INSERT INTO `rabbit_music`.`user_role` (user_id, role_id)
VALUES ('27LCBaYftkMV0YS513mHcw9LCYv', '2');