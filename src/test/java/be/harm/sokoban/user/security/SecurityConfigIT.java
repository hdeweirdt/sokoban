package be.harm.sokoban.user.security;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SecurityConfigIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    public void shouldAllowAPIcallsToEveryone() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/users").accept(MediaType.ALL))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void shouldAllowIndexToEveryone() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.ALL))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void shouldAllowCreateNewUserToEveryone() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/new").accept(MediaType.ALL))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(authorities = "users:read")
    public void shouldAllowRequestingAllUsersWhenUserHasPermission() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/all").accept(MediaType.ALL))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser()
    public void shouldForbidRequestingAllUsersWhenUserDoesNotHavePermission() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/all").accept(MediaType.ALL))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(authorities = "game:play")
    public void shouldAllowRequestingGamesWhenUserHasGAME_PLAYPermission() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/games").accept(MediaType.ALL))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser()
    public void shouldForbidRequestingGamesWithoutGAME_PLAYPermission() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/games").accept(MediaType.ALL))
                .andExpect(status().isForbidden());
    }

}
