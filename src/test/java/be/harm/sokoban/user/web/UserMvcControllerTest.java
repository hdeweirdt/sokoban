package be.harm.sokoban.user.web;

import be.harm.sokoban.user.User;
import be.harm.sokoban.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class UserMvcControllerTest {

    @Mock
    UserService userService;

    @InjectMocks
    private UserMvcController userController;


    @Mock
    Model model;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getUsersShouldReturnAllUsersView() throws Exception {
        // Given
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        // When
        mockMvc.perform(get("/users"))
                // Then
                .andExpect(status().isOk())
                .andExpect(view().name("users/all"));
    }

    @Test
    public void getUsersShouldAddAllUsersInServiceToModel() {
        // Given
        ArgumentCaptor<Set<User>> argumentCaptor = ArgumentCaptor.forClass(Set.class);
        User user = mock(User.class);

        when(userService.findAll()).thenReturn(Set.of(user));

        // When
        userController.getUsers(model);

        // Then
        verify(model, times(1)).addAttribute(eq("users"), argumentCaptor.capture());
        assertTrue(argumentCaptor.getValue().contains(user));
        assertEquals(1, argumentCaptor.getValue().size());
    }

}