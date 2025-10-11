package com.digenty.app.utils.jwt.data;

import lombok.Data;

@Data
public class Application {

    private Long id;
    private Long companyId;
    private Long adminId;
    private String name;
    private String company;
    private String country;
}
