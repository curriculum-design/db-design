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
    `room_number` INT(32)      NOT NULL COMMENT '房间号',
    `room_type`   VARCHAR(256) NOT NULL DEFAULT '普通房' COMMENT '房间类型',
    `telephone`   VARCHAR(128) NOT NULL COMMENT '房间电话',
    `price`       INT(32)      NOT NULL COMMENT '价格/天',
    `status`      TINYINT      NOT NULL DEFAULT 0 COMMENT '房间状态（0-空闲，1-已入住）',
    `create_time` DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_delete`   TINYINT      NOT NULL DEFAULT 0 COMMENT '逻辑删除（0-正常，1-已删除）',
    PRIMARY KEY (`id`),
    constraint room_uk unique (room_number)
) default char set = utf8
    COMMENT '房间信息表';

CREATE TABLE IF NOT EXISTS `user_room`
(
    `id`          BIGINT   NOT NULL AUTO_INCREMENT COMMENT '主键',
    `user_id`     BIGINT   NOT NULL COMMENT '用户id',
    `room_id`     BIGINT   NOT NULL COMMENT '房间id',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `finish_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '入住截止时间',
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_delete`   TINYINT  NOT NULL DEFAULT 0 COMMENT '逻辑删除（0-正常，1-已删除）',
    PRIMARY KEY (`id`)
) COMMENT '用户房间关联表';

CREATE TABLE IF NOT EXISTS `room_item`
(
    `id`    BIGINT          NOT NULL AUTO_INCREMENT COMMENT '主键' ,
    `Room_id`          BIGINT           NOT NULL COMMENT '房间id号',
    `Room_num`         BIGINT          NOT NULL COMMENT '客房号',
    `item_name`       varchar(256)    NOT NULL COMMENT '房间物品名称',
    `item_status`     TINYINT        NOT NULL DEFAULT 0 COMMENT '房间物品状态（0-正常出售，1-售空）',
    `item_price`      INT(32)         NOT NULL COMMENT '房间物品单价',
    `item_num`        INT(32)         NOT NULL COMMENT '房间物品数量',
    `item_sell`       INT(32)         NOT NULL DEFAULT 0 COMMENT '物品消耗数量',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_delete`   TINYINT  NOT NULL DEFAULT 0 COMMENT '逻辑删除（0-正常，1-已删除）',
    PRIMARY KEY (id)

) default char set = utf8
    COMMENT '客房物品表';

CREATE TABLE IF NOT EXISTS `bill`
(
    `id`    BIGINT          NOT NULL AUTO_INCREMENT COMMENT '主键',
    `Room_id`         BIGINT          NOT NULL COMMENT '客房ID',
    `User_id`         BIGINT          NOT NULL COMMENT '用户ID',
    `price`           BIGINT           NOT NULL COMMENT '总共价格',
    `is_pay`        TINYINT      NOT NULL DEFAULT 1 COMMENT '是否支付（0-已经支付，1-未支付）',
    `create_time`   DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`   DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_delete`     TINYINT      NOT NULL DEFAULT 0 COMMENT '逻辑删除（0-正常，1-已删除）',
    PRIMARY KEY (id)
) COMMENT '订单表';


CREATE TABLE IF NOT EXISTS `user_item`
(
    `id`              BIGINT          NOT NULL AUTO_INCREMENT COMMENT '主键',
    `User_id`         BIGINT          NOT NULL COMMENT '用户ID' ,
    `item_name`       varchar(256)    NOT NULL COMMENT '物品名称',
    `item_price`      INT(32)         NOT NULL COMMENT '物品单价',
    `use_num`         INT(32)         NOT NULL COMMENT '物品使用数量',
    `create_time`     DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`     DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_delete`       TINYINT         NOT NULL DEFAULT 0 COMMENT '逻辑删除（0-正常，1-已删除）',
    PRIMARY KEY (id)
)COMMENT '用户消耗物品表';

# 触发器
# 当向用户-房间关联表插入数据的时候，会自动将房间状态更改为已入住
CREATE TRIGGER user_open_room
    AFTER INSERT
    ON user_room
    FOR EACH ROW
BEGIN
    UPDATE room
    SET status = 1
    WHERE id = NEW.room_id
      AND is_delete = 0;
END;

# 当用户退房时会自动将房间状态设置为未入住
CREATE TRIGGER user_out_room
    AFTER UPDATE
    ON user_room
    FOR EACH ROW
BEGIN
    IF NEW.is_delete != OLD.is_delete THEN
        UPDATE room
        SET status = 0
        WHERE id = NEW.room_id
          AND is_delete = 0;
    END IF;
END;

# 当用户退房的时候将房间内的物品数量进行更新
CREATE TRIGGER user_out_item_update
    AFTER UPDATE
    on room_item
    FOR EACH ROW
BEGIN
    IF NEW.is_delete != OLD.is_delete THEN
        UPDATE room_item
            SET item_num = item_num-room_item.item_sell
        WHERE id = NEW.id AND is_delete = 0;
    end if;
end;


#创建用户视图
create view view_user(客户昵称,客户姓名,性别,客户身份证号)
as select nickname,username,gender,card_id from user;

#创建客房视图
create view view_room(房间号,房间类型,客房单价,客房状态,客房电话)
as select room_number,room_type,price,status,telephone from room;

#创建客房住宿信息视图
create view view_living(客户号,客房号,入住日期,结算日期,住宿时间,房费)
as select user_id,room_id,UR.create_time,UR.finish_time,TIMESTAMPDIFF(MINUTE,UR.create_time,UR.finish_time),price
   from user_room UR
            JOIN room R ON UR.room_id=R.id
   GROUP BY UR.room_id;

#创建房间物品视图
create view view_RoomItem(客房号,客房物品编号,客房物品名称,物品状态,物品单价,物品数量)
as select Room_num,id,item_name,item_status,item_price,item_num
   from room_item;

#计算总开销视图
CREATE VIEW view_bill(入住时长,损坏总额,总开销)
as select TIMESTAMPDIFF(MINUTE,ur.create_time,ur.finish_time),SUM(item_price),(R.price+SUM(item_price))
   from room R RIGHT JOIN db_design.user_room ur on R.id = ur.room_id
               join room_item where item_status = 2;

#创建主键的索引
#CREATE UNIQUE INDEX user_no on user(id);
#CREATE UNIQUE INDEX room_no on room(id);
#CREATE UNIQUE INDEX user_room_no on user_room(id);
#CREATE UNIQUE INDEX room_item_no on room_item(id);

CREATE UNIQUE INDEX Telephone on user(telephone);
CREATE INDEX Password on user(password);
#创建电话和密码
CREATE INDEX idx_password_telephone on user(telephone,password);
CREATE UNIQUE INDEX RoomNumber on room(room_number);
# CREATE UNIQUE INDEX ItemName on room_item(item_name);
# ALTER TABLE room_item drop INDEX ItemName;

CREATE UNIQUE INDEX bill_no on bill(id);
#创建插入数据

CREATE UNIQUE INDEX user_item_on on user_item(id);

# update room set status = 1 and is_delete = 1 where room_number = 509;
#
# delete from room_item WHERE item_name = '香蕉';
#
# INSERT INTO user VALUES (5, 'Bill', 'jiangnan', '6fb98392774684cf867230c436695d94','12345612367'
# ,0,'410304200205032121','2023-06-11 20:27:26','2023-06-11 20:27:26',0,1);

ALTER TABLE user_item ADD CONSTRAINT User_id FOREIGN KEY(User_id) REFERENCES user(id);

ALTER TABLE bill ADD CONSTRAINT Room_id FOREIGN KEY (Room_id) REFERENCES  room(id);
ALTER TABLE bill ADD CONSTRAINT fk_user_id FOREIGN KEY (User_id) REFERENCES  user(id);

ALTER TABLE user_room ADD CONSTRAINT fk2_user_id FOREIGN KEY (user_id) REFERENCES  user(id);
ALTER TABLE user_room ADD CONSTRAINT fk2_room_id FOREIGN KEY (room_id) REFERENCES  room(id);

ALTER TABLE room_item ADD CONSTRAINT fk3_room_id FOREIGN KEY (Room_id) REFERENCES  room(id);

INSERT INTO room VALUES (12,608,'电竞房',608,250,0,current_timestamp,current_timestamp,0);

CREATE USER 'admin'@'localhost' IDENTIFIED BY '123456';

GRANT SELECT, INSERT,UPDATE,DELETE ON db_design.view_bill TO 'admin'@'localhost';
GRANT SELECT, INSERT,UPDATE,DELETE ON db_design.view_living TO 'admin'@'localhost';
GRANT SELECT, INSERT,UPDATE,DELETE ON db_design.view_room TO 'admin'@'localhost';
GRANT SELECT, INSERT,UPDATE,DELETE ON db_design.view_roomitem TO 'admin'@'localhost';
GRANT SELECT, INSERT,UPDATE,DELETE ON db_design.view_user TO 'admin'@'localhost';

CREATE USER 'wcx'@'localhost' IDENTIFIED BY '123456';

GRANT SELECT, INSERT,UPDATE,DELETE ON db_design.view_bill TO 'wcx'@'localhost';
GRANT SELECT, INSERT,UPDATE,DELETE ON db_design.view_living TO 'wcx'@'localhost';
GRANT SELECT, INSERT,UPDATE,DELETE ON db_design.view_room TO 'wcx'@'localhost';
GRANT SELECT, INSERT,UPDATE,DELETE ON db_design.view_roomitem TO 'wcx'@'localhost';
GRANT SELECT, INSERT,UPDATE,DELETE ON db_design.view_user TO 'wcx'@'localhost';

CREATE USER 'zzs'@'localhost' IDENTIFIED BY '123456';

GRANT SELECT, INSERT,UPDATE,DELETE ON db_design.view_bill TO 'zzs'@'localhost';
GRANT SELECT, INSERT,UPDATE,DELETE ON db_design.view_living TO 'zzs'@'localhost';
GRANT SELECT, INSERT,UPDATE,DELETE ON db_design.view_room TO 'zzs'@'localhost';
GRANT SELECT, INSERT,UPDATE,DELETE ON db_design.view_roomitem TO 'zzs'@'localhost';
GRANT SELECT, INSERT,UPDATE,DELETE ON db_design.view_user TO 'zzs'@'localhost';

CREATE USER 'jn'@'localhost' IDENTIFIED BY '123456';

GRANT SELECT, INSERT,UPDATE,DELETE ON db_design.view_bill TO 'jn'@'localhost';
GRANT SELECT, INSERT,UPDATE,DELETE ON db_design.view_living TO 'jn'@'localhost';
GRANT SELECT, INSERT,UPDATE,DELETE ON db_design.view_room TO 'jn'@'localhost';
GRANT SELECT, INSERT,UPDATE,DELETE ON db_design.view_roomitem TO 'jn'@'localhost';
GRANT SELECT, INSERT,UPDATE,DELETE ON db_design.view_user TO 'jn'@'localhost';

CREATE USER 'lzh'@'localhost' IDENTIFIED BY '123456';

GRANT SELECT, INSERT,UPDATE,DELETE ON db_design.view_bill TO 'lzh'@'localhost';
GRANT SELECT, INSERT,UPDATE,DELETE ON db_design.view_living TO 'lzh'@'localhost';
GRANT SELECT, INSERT,UPDATE,DELETE ON db_design.view_room TO 'lzh'@'localhost';
GRANT SELECT, INSERT,UPDATE,DELETE ON db_design.view_roomitem TO 'lzh'@'localhost';
GRANT SELECT, INSERT,UPDATE,DELETE ON db_design.view_user TO 'lzh'@'localhost';
