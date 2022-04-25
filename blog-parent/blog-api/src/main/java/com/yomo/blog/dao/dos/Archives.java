package com.yomo.blog.dao.dos;

import lombok.Data;

/**
 * <p>
 * 文章归档类
 * </p>
 *
 * @author yomo
 * @since 2022/4/12
 */
@Data
public class Archives {
    private Integer year;
    private Integer month;
    private Long count;
}
