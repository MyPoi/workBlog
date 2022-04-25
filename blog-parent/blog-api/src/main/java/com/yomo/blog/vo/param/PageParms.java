package com.yomo.blog.vo.param;

import lombok.Data;

/**
 * <p>
 * 分页功能参数
 * </p>
 *
 * @author yomo
 * @since 2022/4/6
 */
@Data
public class PageParms {
    private int page = 1;
    private int pageSize = 10;

    private Long categoryId;
    private Long tagId;

    private String year;
    private String month;

    public String getMonth() {
        if (this.month != null && this.month.length() == 1)
            return "0" + this.month;
        return month;
    }
}
