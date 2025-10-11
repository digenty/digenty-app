package com.digenty.app.api.permissions;

import com.digenty.app.commons.core.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Permission extends BaseEntity {

    @Column(unique = true)
    private String name;
    private String alias;

    @Override
    public String toString() {
        return "Permission{" +
                "name='" + name + '\'' +
                ", alias='" + alias + '\'' +
                "} " + super.toString();
    }

}
