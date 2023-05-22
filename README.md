# 宾馆客房管理系统

后端接口地址：localhost:8081

接口文档地址：localhost:8081/doc.html

## 技术

### 前端

* 框架：Vue 3
* 组件库：Element Plus
* 请求：Axios
* 状态管理：Pinia

### 后端

* 数据库：MySQL
* 框架：Spring Boot、MyBatis

## 系统设计

关系：
客户信息表：id（主件）、昵称（用户名）、姓名、手机号telephone、性别、身份号、password（密码）、create_time、update_time、is_delete；
管理员信息（不建立表）：（在直接加一个‘特征’）；
房间表：房间号、目前入住、座机电话、房间类型（char类型）、price（价格）、create_time、update_time、is_delete
、status（是否已经入住）；
用户房间关联表：id（主件）、用户ID、房间ID（房间号）、create_time、update_time、is_delete、time（预期时间）；
物品信息：（未开发，待定）

管理员若干，差不多7-10个；
用户手动输入若干，15个；
房间的话：100个（看情况）

前端：
第一个页面：登录注册；注册信息：昵称（用户名）、telephone、姓名、性别、身份号、password（密码）；判断用户身份、
进入页面：展示出所有的房间（房间信息）、入住退房的按钮、展示不同类型的房间
退房的页面：费用的计算，怎么计算，

管理员：展示信息、管理房间、用户、到预期时间的话发短信提醒可能需要退房了。

待定：
物品信息、押金、费用计算的话（需要加上房间内消费的物品）

## 数据库表

### 用户信息表

```sql
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
```

### 房间信息表

```sql
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
```

### 用户房间关联表

```sql
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
```

## 触发器

1. 在用户开房后，需要向用户房间关联表中插入一条数据，如果插入成功，则触发一条更新语句：将房间表中对应的房间状态设置为已入住。

```SQL
CREATE TRIGGER user_open_room
    AFTER INSERT
    ON user_room
    FOR EACH ROW
BEGIN
    UPDATE room
    SET status = 1
    WHERE id = NEW.room_id AND is_delete = 0;
END;
```

2. 在用户退房的时候，需要将用户房间关联表的相关数据删除，并触发一条更新语句：将房间表中对应的房间状态设置为未入住

```sql
CREATE TRIGGER user_out_room
    AFTER UPDATE
    ON user_room
    FOR EACH ROW
BEGIN
    IF NEW.is_delete != OLD.is_delete THEN
        UPDATE room
        SET status = 0
        WHERE id = NEW.room_id AND is_delete = 0;
    END IF;
END;
```





## 索引

所有表都使用 id 作为主键索引

