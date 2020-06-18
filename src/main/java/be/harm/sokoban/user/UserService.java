package be.harm.sokoban.user;

import java.util.Set;

public interface UserService {
    Set<User> findAll();

    User saveUser(User user);

    User saveAdmin(User user);
}
