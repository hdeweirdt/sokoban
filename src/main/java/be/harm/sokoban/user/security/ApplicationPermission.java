package be.harm.sokoban.user.security;

import lombok.Getter;

public enum ApplicationPermission {
    GAME_CREATE("game:create"),
    GAME_PLAY("game:play"),
    GAME_CHANGE("game:change"),
    USERS_READ("users:read"),
    USERS_CHANGE("users:change");

    @Getter
    private final String permission;

    ApplicationPermission(String permission) {
        this.permission = permission;
    }
}
