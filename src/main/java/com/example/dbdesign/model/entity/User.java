package com.example.dbdesign.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户表
 * @author zzs
 * @TableName user
 */
@Data
public class User implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 姓名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 电话号码
     */
    private String telephone;

    /**
     * 性别（0-女，1-男）
     */
    private Integer gender;

    /**
     * 身份证号
     */
    private String cardId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 逻辑删除（0-正常，1-已删除）
     */
    private Integer isDelete;

    /**
     * 角色（0-普通用户，1-管理员）
     */
    private Integer userRole;
}