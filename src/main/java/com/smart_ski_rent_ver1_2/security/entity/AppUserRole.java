package com.smart_ski_rent_ver1_2.security.entity;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import com.google.common.collect.Sets;
import java.util.Set;
import java.util.stream.Collectors;
import static com.smart_ski_rent_ver1_2.security.entity.AppUserPermission.*;

public enum AppUserRole {

    COMPANY(Sets.newHashSet()),
    DEVEL(Sets.newHashSet(COMPANY_WRITE,COMPANY_READ)),
    ADMIN(Sets.newHashSet(COMPANY_READ));

    private final Set<AppUserPermission> permissions;

    AppUserRole(Set<AppUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<AppUserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permision -> new SimpleGrantedAuthority(permision.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
