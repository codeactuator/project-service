package com.codeactuator.rocket.config;

public enum  ApplicationUserPermission {

    PROJECT_READ("project:read"),
    PROJECT_WRITE("project:write"),
    TASK_READ("task:read"),
    TASK_WRITE("task:write");

    private final String permission;

    ApplicationUserPermission(String s) {
        this.permission = s;
    }

    public String getPermission() {
        return permission;
    }
}
