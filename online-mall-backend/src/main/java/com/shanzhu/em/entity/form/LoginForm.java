package com.shanzhu.em.entity.form;

import lombok.Data;

/**
 * 用户登录表单
 *
 * @author: ZhangDaYe
 * @date: 2024-08-24
 */
@Data
public class LoginForm {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

}
