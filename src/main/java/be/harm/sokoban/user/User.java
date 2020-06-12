package be.harm.sokoban.user;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class User {
    private static final int MIN_USERNAME_LENGTH = 8;
    private static final int MIN_PASSWORD_LENGTH = 8;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    private String userName;

    @Getter
    private String password;

    @Getter
    @Setter
    private String firstName;

    @Getter
    @Setter
    private String lastName;

    @Getter
    @Setter
    private boolean isAdmin = false;

    public User(String userName, String password) {
        setUserName(userName);
        setPassword(password);
    }

    public void setUserName(String userName) {
        if(userName == null || userName.isEmpty()) {
            throw new IllegalArgumentException("Empty username");
        }
        if(userName.length() < MIN_USERNAME_LENGTH) {
            throw new IllegalArgumentException("Username must be " + MIN_USERNAME_LENGTH + " characters long");
        }
        this.userName=userName;
    }

    public void setPassword(String password) {
        if(password == null || password.isEmpty()) {
            throw new IllegalArgumentException();
        }
        if(password.length() < MIN_PASSWORD_LENGTH) {
            throw new IllegalArgumentException("Password must be " + MIN_PASSWORD_LENGTH + " characters long");
        }
        if(password.toLowerCase().equals(password)) {
            throw new IllegalArgumentException("Password must contain at least 1 upper case character");
        }
        if(password.toUpperCase().equals(password)) {
            throw new IllegalArgumentException("Password must contain at least 1 lower case character");
        }
        if(!password.matches(".*\\d.*")) {
            throw new IllegalArgumentException("Password must contain at least 1 number");
        }
        this.password = password;
    }
}
