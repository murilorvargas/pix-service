package com.pix.pix_service.api.schemas.response;

import java.util.List;

public class PaginatedPixKeyResponseSchema {

    private List<ListPixKeyResponseSchema> data;
    private PaginationSchema pagination;

    public PaginatedPixKeyResponseSchema() {
    }

    public PaginatedPixKeyResponseSchema(
            List<ListPixKeyResponseSchema> data,
            PaginationSchema pagination
    ) {
        this.data = data;
        this.pagination = pagination;
    }

    public List<ListPixKeyResponseSchema> getData() {
        return data;
    }

    public PaginationSchema getPagination() {
        return pagination;
    }

}
