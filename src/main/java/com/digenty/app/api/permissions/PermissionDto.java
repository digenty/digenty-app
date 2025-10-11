package com.digenty.app.api.permissions;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PermissionDto {

    @Pattern(regexp = "\\b[a-z]+(?:_[a-z]+)+\\b", message = "Name should only be in this format 'view_all_devices'")
    private String name;

    private String type;
}
