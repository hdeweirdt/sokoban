package be.harm.sokoban.user.web;

import be.harm.sokoban.testutil.TestSecurityConfiguration;
import be.harm.sokoban.user.UserService;
import be.harm.sokoban.user.commands.CreateUserCommand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserMvcController.class)
@ContextConfiguration(classes = {TestSecurityConfiguration.class})
@AutoConfigureMockMvc
public class UserMvcControllerIT {

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldAllowRegisterNewUserToEveryone() throws Exception {
        mockMvc.perform(get("/users/new"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldRedirectToLoginWhenUnauthenticatedUserRequestsAllUsers() throws Exception {
        mockMvc.perform(get("/users/all"))
                .andExpect(status().is3xxRedirection())
                // TODO: why can't I use redirectedUrlTemplate("/login") ?
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    public void shouldReturnToFormWhenInvalidPasswordGiven() throws Exception {
        CreateUserCommand createUser = CreateUserCommand.builder()
                .userName("ValidUserName")
                .password("short").build();

        mockMvc.perform(MockMvcRequestBuilders.post("/users/new", createUser))
                .andExpect(redirectedUrl("http://localhost/users/new"));

    }
}
