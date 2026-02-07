package com.pix.pix_service.api.schemas.response;

import java.util.List;

public class PaginatedDynamicInstantQrCodeResponseSchema {

    private List<ListDynamicInstantQrCodeResponseSchema> data;
    private PaginationSchema pagination;

    public PaginatedDynamicInstantQrCodeResponseSchema() {
    }

    public PaginatedDynamicInstantQrCodeResponseSchema(
            List<ListDynamicInstantQrCodeResponseSchema> data,
            PaginationSchema pagination
    ) {
        this.data = data;
        this.pagination = pagination;
    }

    public List<ListDynamicInstantQrCodeResponseSchema> getData() {
        return data;
    }

    public PaginationSchema getPagination() {
        return pagination;
    }

}
