package com.digenty.app.utils.jwt.data;

//import com.termii.common.data.TermiiConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuditDetails implements Serializable {
    private Long userId;
    private String email;
    private String token;

//    public AuditDetails(Long userId, String email, String token) {
//        this.userId = userId;
//        this.email = email;
//        this.token = token;
//    }
}
