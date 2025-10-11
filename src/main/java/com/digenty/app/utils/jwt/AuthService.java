package com.digenty.app.utils.jwt;

import com.digenty.app.utils.jwt.data.AppAuthentication;
import com.digenty.app.utils.jwt.data.AuditDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthService {

    public static AuditDetails currentAuditDetails() {

        Authentication possible = SecurityContextHolder.getContext().getAuthentication();
        if (possible instanceof AppAuthentication auth) {
            AuditDetails audit = new AuditDetails( auth.getUserId(), auth.getEmail(), auth.getToken());
            return audit;
        }
        AuditDetails sysAudit = new AuditDetails(0L, "", "");
        return sysAudit;
    }
}
