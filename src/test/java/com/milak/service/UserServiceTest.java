package com.milak.service;

import com.milak.model.Role;
import com.milak.model.User;
import com.milak.repository.UserRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.verify;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserService userService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void registerUserTest() throws Exception {

        Mockito.when(userRepository.findByUsername(Mockito.anyString()))
                .thenReturn(null);
        Mockito.doNothing().when(userRepository).createUser(Mockito.any(User.class));
        Mockito.doNothing().when(userService).registerUser(Mockito.any(User.class));

        userService.registerUser(getUser());
        verify(userService, Mockito.times(1)).registerUser(Mockito.any(User.class));
    }

    @Test
    void findUserByUsernameTest() throws Exception {
        Mockito.when(userRepository.findByUsername(Mockito.anyString()))
                .thenReturn(getUser());

        Mockito.when(userService.findUserByUsername(Mockito.anyString()))
                .thenReturn(getUser());

        User user = userService.findUserByUsername("username");

        assertNotNull("User not existing", user);
        assertEquals("User not saved properly", user.getUsername(), getUser().getUsername());
    }

    private User getUser() {
        return new User.Builder()
                .setUuid("uuid")
                .setName("name")
                .setSurname("surname")
                .setUsername("username")
                .setRole(Role.USER)
                .setMaxLimit(0)
                .setCurrentLimit(0)
                .build();
    }
}
