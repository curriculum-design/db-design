DROP DATABASE IF EXISTS `db_design`;
CREATE DATABASE `db_design`;
USE `db_design`;
CREATE TABLE IF NOT EXISTS `user`
(
    `id`          BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键',
    `nickname`    VARCHAR(256) NULL COMMENT '昵称',
    `username`    VARCHAR(256) NOT NULL COMMENT '姓名',
    `password`    VARCHAR(256) NULL COMMENT '密码',
    `telephone`   VARCHAR(128) NOT NULL COMMENT '电话号码',
    `gender`      TINYINT      NOT NULL DEFAULT 0 COMMENT '性别',
    `card_id`     VARCHAR(256) NOT NULL COMMENT '身份证号',
    `create_time` DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_delete`   TINYINT      NOT NULL DEFAULT 0 COMMENT '逻辑删除（0-正常，1-已删除）',
    `user_role`   TINYINT      NOT NULL DEFAULT 0 COMMENT '角色（0-普通用户，1-管理员）',
    PRIMARY KEY (`id`)
) COMMENT '用户表';

CREATE TABLE IF NOT EXISTS `room`
(
    `id`          BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键',
    `room_number`     INT(32)      NOT NULL COMMENT '房间号',
    `room_type`   VARCHAR(256) NOT NULL DEFAULT '普通房' COMMENT '房间类型',
    `telephone`   VARCHAR(128) NOT NULL COMMENT '房间电话',
    `price`       INT(32)      NOT NULL COMMENT '价格/天',
    `status`      TINYINT      NOT NULL DEFAULT 0 COMMENT '房间状态（0-空闲，1-已入住）',
    `create_time` DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_delete`   TINYINT      NOT NULL DEFAULT 0 COMMENT '逻辑删除（0-正常，1-已删除）',
    PRIMARY KEY (`id`)
) COMMENT '房间信息表';

CREATE TABLE IF NOT EXISTS `user_room`
(
    `id`          BIGINT   NOT NULL AUTO_INCREMENT COMMENT '主键',
    `user_id`     BIGINT   NOT NULL COMMENT '用户id',
    `room_id`     BIGINT   NOT NULL COMMENT '房间id',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `time`        DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '入住时长',
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_delete`   TINYINT  NOT NULL DEFAULT 0 COMMENT '逻辑删除（0-正常，1-已删除）',
    PRIMARY KEY (`id`)
) COMMENT '用户房间关联表';