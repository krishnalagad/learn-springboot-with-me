package com.revise.transactional.entity;

import java.util.Set;

public enum Role {
    ADMIN(Set.of(Permissions.REVISE_READ, Permissions.REVISE_WRITE, Permissions.REVISE_DELETE)),
    USER(Set.of(Permissions.REVISE_READ));

    private final Set<Permissions> permissions;
    Role(Set<Permissions> permissions) {
        this.permissions = permissions;
    }

    public Set<Permissions> getPermissions() {
        return permissions;
    }
}