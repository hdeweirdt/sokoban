package be.harm.sokoban.user;

import be.harm.sokoban.user.roles.Role;
import be.harm.sokoban.user.roles.RoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class UserJPAServiceTest {

    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private UserJPAService userJPAService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        // Thoughts: change to dummy implementations?
        when(passwordEncoder.encode(anyString())).then(returnsFirstArg());
        when(roleRepository.findByName(Role.ADMIN)).thenReturn(new Role(Role.ADMIN));
        when(roleRepository.findByName(Role.USER)).thenReturn(new Role(Role.USER));
        when(userRepository.save(any(User.class))).then(returnsFirstArg());
    }


    @Test
    void shouldGiveNewAdminUserRights() {
        // Given
        User user = new User("Username", "Wachtwoord1");

        // When
        User resultingUser = userJPAService.saveAdmin(user);

        // Then
        assertTrue(resultingUser.getRoles().size() >= 1);
        assertTrue(resultingUser.getRoles().stream()
                .anyMatch((role -> role.getName().equals(Role.USER))));
    }
    @Test
    void shouldGiveNewAdminAdminRights() {
        // Given
        User user = new User("Username", "Wachtwoord1");

        // When
        User resultingUser = userJPAService.saveAdmin(user);

        // Then
        assertTrue(resultingUser.getRoles().size() >= 1);
        assertTrue(resultingUser.getRoles().stream()
                .anyMatch((role -> role.getName().equals(Role.ADMIN))));
    }

    @Test
    void shouldGiveNewUserOnlyUserRights() {
        // Given
        User user = new User("Username", "Wachtwoord1");

        // When
        User resultingUser = userJPAService.saveUser(user);

        // Then
        assertEquals(1, resultingUser.getRoles().size());
        assertTrue(resultingUser.getRoles().stream()
                .anyMatch((role -> role.getName().equals(Role.USER))));
    }
}