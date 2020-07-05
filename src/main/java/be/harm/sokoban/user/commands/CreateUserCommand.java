package be.harm.sokoban.user.commands;

import be.harm.sokoban.user.User;
import lombok.Data;

@Data
public class CreateUserCommand {
    private String userName;
    private String password;
    private String firstName;
    private String lastName;

    public User mapToUser() {
        return User.builder()
                .userName(userName)
                .password(password)
                .firstName(firstName)
                .lastName(lastName)
                .build();
    }
}
