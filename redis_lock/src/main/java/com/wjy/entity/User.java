package com.wjy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author wangjingyang
 * @date 2021-01-24 15:04
 */
@Data
@TableName("t_user")
public class User {
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    private String name;
    private String password;
}
