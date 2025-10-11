package com.digenty.app.commons.core;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.util.Date;

public class BaseEntityListener {

    @PrePersist
    public void touchForCreate(BaseEntity data) {
        Date createDate = new Date();
        data.setCreatedAt(createDate);
        data.setUpdatedAt(createDate);
        data.setActive(Boolean.TRUE);
    }

    @PreUpdate
    public void touchForUpdate(BaseEntity data) {
        data.setUpdatedAt(new Date());
    }

}
