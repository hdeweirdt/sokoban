package be.harm.sokoban.user;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UserTest {

    private User user;

    private String validPassword = "Password123";
    private String validUserName= "Username";

    @AfterEach
    private void cleanUp() {
        user = null;
    }


    @Test
    public void shouldNotBeAdminByDefault() {
        user = new User(validUserName, validPassword);

        assertFalse(user.)
    }
    @Test
    public void shouldAcceptValidUserNameAndPassword() {
       user = new User(validUserName, validPassword);
    }

    @Test
    public void shouldNotAllowPasswordWithoutNumber() {
        String password = "nonumbershere";

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            user = new User(validUserName, password);
        });
    }

    @Test
    public void shouldNotAllowOnlyUppercasePassword() {
        String password = "LONGENOUGH1";

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            user = new User(validUserName, password);
        });
    }

    @Test
    public void shouldNotAllowOnlyLowercasePassword() {
        String password = "longenough1";

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            user = new User(validUserName, password);
        });
    }

    @Test
    public void shouldNotAllowTooShortPassword() {
        String password = "short1";

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            user = new User(validUserName, password);
        });
    }

    @Test
    public void shouldNotAllowNullPassword() {
        String password = null;

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            user = new User(validUserName, password);
        });
    }


    @Test
    public void shouldNotAllowNullUsername() {
        String username = null;

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            user = new User(username, validPassword);
        });
    }

}