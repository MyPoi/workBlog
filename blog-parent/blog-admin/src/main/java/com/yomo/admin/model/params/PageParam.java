package com.yomo.admin.model.params;

/**
 * <p>
 * TODO:请用一句话描述
 * </p>
 *
 * @author yomo
 * @since 2022/4/23
 */
public class PageParam {
    
    private int currentPage;
    private int pageSize;
    private String queryString;

    public Integer getCurrentPage() {
        return currentPage;
    }

    public PageParam setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
        return this;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public PageParam setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public String getQueryString() {
        return queryString;
    }

    public PageParam setQueryString(String queryString) {
        this.queryString = queryString;
        return this;
    }

    @Override
    public String toString() {
        return "PageParam{" +
                "currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                ", queryString='" + queryString + '\'' +
                '}';
    }
}
