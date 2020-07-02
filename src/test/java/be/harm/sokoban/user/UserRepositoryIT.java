package be.harm.sokoban.user;

import be.harm.sokoban.user.security.ApplicationRole;
import org.apache.commons.collections4.SetUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserRepositoryIT {
    @Autowired
    private UserRepository userRepository;

    @Test
    void injectedComponentsAreNotNull(){
        assertThat(userRepository).isNotNull();
    }

    @Test
    void usersShouldGetHaveAllRolesWhenLoaded() {
        // Given
        User user = new User("UserName1", "Wachtwoor1");
        user.setRoles(SetUtils.hashSet(ApplicationRole.ADMIN, ApplicationRole.PLAYER));
        User savedUser = userRepository.save(user);

        // When
        User foundUser = userRepository.findByUserName(savedUser.getUserName());

        //Then
        assertEquals(2,foundUser.getRoles().size());
    }

}
