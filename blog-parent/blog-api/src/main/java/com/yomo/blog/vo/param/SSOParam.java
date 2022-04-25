package com.yomo.blog.vo.param;

import lombok.Data;

/**
 * <p>
 * 登录功能参数
 * </p>
 *
 * @author yomo
 * @since 2022/4/13
 */
@Data
public class SSOParam {
    
    private String account;
    
    private String password;
    
    private String nickname;
}
