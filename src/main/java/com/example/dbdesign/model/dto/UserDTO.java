package com.example.dbdesign.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zzs
 * @date 2023/5/21 16:41
 */
@Data
public class UserDTO implements Serializable {
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
     * 角色（0-普通用户，1-管理员）
     */
    private Integer userRole;
}
