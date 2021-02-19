package com.wjy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author wangjingyang
 * @date 2021-02-19 1:08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String name;
    private String age;
    private Date birthDay;

}
