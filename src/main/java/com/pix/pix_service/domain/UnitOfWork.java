package com.pix.pix_service.domain;

public interface UnitOfWork {

    void begin();

    void commit();

    void rollback();
}
