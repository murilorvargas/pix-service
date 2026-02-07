package com.pix.pix_service.api.schemas.response;

public class PaginationSchema {

    private Integer page;
    private Integer pageSize;

    public PaginationSchema() {
    }

    public PaginationSchema(Integer page, Integer pageSize) {
        this.page = page;
        this.pageSize = pageSize;
    }

    public Integer getPage() {
        return page;
    }

    public Integer getPageSize() {
        return pageSize;
    }
}
