package com.wjy.httpclient.entity;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author wangjingyang
 * @date 2021-01-02 15:54
 */
public class UserEntity {

    @ApiModelProperty("手机号")
    private String mobile;
    @ApiModelProperty(value="自增id")
    private Long id;
    @ApiModelProperty("名称")
    private String name;
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("盐")
    private String salt;
    @ApiModelProperty("角色")
    private String roles;
    private Integer status;
    @ApiModelProperty("登录账号")
    private String  loginName;
    private Date createAt;
    private Date  updateAt;

    @ApiModelProperty("操作员说明")
    private String descriptor;
}
