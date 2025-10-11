package com.digenty.app.commons.core;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.util.StringUtils;

import java.sql.Types;
import java.util.Date;
import java.util.UUID;

@MappedSuperclass
@EntityListeners(BaseEntityListener.class)
@Data
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "uuid", unique = true, nullable = false, columnDefinition = "char(36)")
    @JdbcTypeCode(Types.VARCHAR)
    private UUID uuid = UUID.randomUUID();

    @Column(name = "active", nullable = false)
    private Boolean active = Boolean.TRUE;

    @Version
    private Long version;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Africa/Lagos")
    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Africa/Lagos")
    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private Date updatedAt;

    public BaseEntity(Long id, UUID uuid, Boolean active, Long version, Date createdAt, Date updatedAt) {
        this.id = id;
        this.uuid = uuid;
        this.active = active;
        this.version = version;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public BaseEntity() {
    }

    public void copyForUpdate(Object source, String... ignoreProperties) {
        String ignoreList = "createDate,version,modifiedDate,id,uuid,active,createdBy,modifiedBy,applicationId,createdAt";
        String[] ignores = StringUtils.concatenateStringArrays(ignoreProperties, ignoreList.split(","));
        BeanUtils.copyProperties(source, this, ignores);
    }
}
