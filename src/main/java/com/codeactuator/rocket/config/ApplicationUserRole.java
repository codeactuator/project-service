package com.codeactuator.rocket.config;

import com.google.common.collect.Sets;

import java.util.Set;

import static com.codeactuator.rocket.config.ApplicationUserPermission.*;

public enum ApplicationUserRole {

    EMPLOYEE(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(PROJECT_READ, PROJECT_WRITE, TASK_READ, TASK_WRITE));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }
}
