package be.harm.sokoban.user.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum ApplicationRole {
    PLAYER(Collections.singletonList(ApplicationPermission.GAME_PLAY)),
    ADMIN(Arrays.asList(
            ApplicationPermission.GAME_CHANGE,
            ApplicationPermission.GAME_CREATE,
            ApplicationPermission.USERS_CHANGE,
            ApplicationPermission.USERS_READ));

    private final List<ApplicationPermission> permissions;

    ApplicationRole(List<ApplicationPermission> permissions) {
        this.permissions = permissions;
    }

    /**
     * Returns all authorities associated with a role.
     *
     * @return the authoroties (by name) and the role (prefixed by ROLE_)
     */
    public Set<SimpleGrantedAuthority> getGrantedAuthorities () {
        var authorities =  permissions.stream()
                .map(permission -> new SimpleGrantedAuthority(permission.name()))
                .collect(Collectors.toSet());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

        return authorities;
    }
    @Converter
    public static class ApplicationRoleConverter implements AttributeConverter<Set<ApplicationRole>, String> {

        private static final String DELIMITER = ";";

        @Override
        public String convertToDatabaseColumn(Set<ApplicationRole> applicationRoles) {
            if (applicationRoles.isEmpty()) {
                return null;
            } else {
                return applicationRoles.stream()
                        .map(Enum::name)
                        .collect(Collectors.joining(DELIMITER));
            }
        }

        @Override
        public Set<ApplicationRole> convertToEntityAttribute(String s) {
            if (StringUtils.isEmpty(s)) {
                return EnumSet.noneOf(ApplicationRole.class);
            } else {
                return Stream.of(s.split(DELIMITER))
                        .map(ApplicationRole::valueOf)
                        .collect(Collectors.toSet());
            }
        }
    }
}
