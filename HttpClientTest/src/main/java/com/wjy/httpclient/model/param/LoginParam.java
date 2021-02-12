package com.wjy.httpclient.model.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author wangjingyang
 * @date 2021-01-02 15:16
 */
@Data
public class LoginParam {
    private String loginName;
    private String password;
}
