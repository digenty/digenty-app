package com.digenty.app.utils.jwt.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserApplication {
    private User user;
    private Application application;
    private Boolean isAdmin;
}
