package com.yomo.admin.vo;

import java.util.List;

/**
 * <p>
 * TODO:请用一句话描述
 * </p>
 *
 * @author yomo
 * @since 2022/4/23
 */
public class PageResult<T> {
    
    private List<T> list;
    private Long total;

    public List<T> getList() {
        return list;
    }

    public PageResult<T> setList(List<T> list) {
        this.list = list;
        return this;
    }

    public Long getTotal() {
        return total;
    }

    public PageResult<T> setTotal(Long total) {
        this.total = total;
        return this;
    }

    @Override
    public String toString() {
        return "PageResult{" +
                "list=" + list +
                ", total=" + total +
                '}';
    }
}
