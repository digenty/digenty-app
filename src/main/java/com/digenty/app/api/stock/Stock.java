package com.digenty.app.api.stock;

import com.digenty.app.api.branches.Branch;
import com.digenty.app.api.users.User;
import com.digenty.app.commons.core.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;


@EqualsAndHashCode(callSuper = true)
@Entity
@Slf4j
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Stock extends BaseEntity{
    private String name;
    private String description;
    private String category;
    private String imagePath;
    private String unit;
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal costPrice;
    private String status;
    private String reason;
    private String summary;
    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}


