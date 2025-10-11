package com.digenty.app.api.roles;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import java.util.List;

@Data
public class RoleDto {

    @Pattern(regexp = "\\b[a-z]+(?:_[a-z]+)+\\b", message = "Name should only be in this format 'view_all_devices'")
    private String name;

    @JsonProperty("permission_ids")
    private List<Long> permissionIds;
}
