package com.example.services;

import com.example.model.User;
import com.example.model.authorization.EnumRole;
import com.example.model.authorization.Role;
import com.example.model.dto.UserDto;
import com.example.model.repositories.RoleRepository;
import com.example.model.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    UserRepository userRepository;
    @Mock
    RoleRepository roleRepository;
    @Mock
    public PasswordEncoder passwordEncoder;
    @InjectMocks
    UserServiceImpl userService;

    public static final String FIRST_NAME = "TEST";
    public static final Long ID = 1L;
    public static final String CODED_PASSWORD = "########";


    User returnUser;

    UserDto returnUserDto;

    Role returnRole;

    @BeforeEach
    void setUp() {
        returnRole = Role.builder().name(EnumRole.USER.name()).build();
        returnUserDto = UserDto.builder().firstName(FIRST_NAME).build();
        returnUser = User.builder()
                .firstName(FIRST_NAME)
                .id(ID)
                .roles(Set.of(returnRole))
                .build();
    }

    @Test
    void save() {
        userService.save(returnUser);
        verify(userRepository,times(1)).save(any(User.class));
    }

    @Test
    void savedUserPasswordShouldBeCoded(){
        when(passwordEncoder.encode(any())).thenReturn(CODED_PASSWORD);
        returnUser.setPassword(passwordEncoder.encode("Password"));
        assertNotNull(returnUser.getPassword());
        assertEquals(CODED_PASSWORD,returnUser.getPassword());
    }

    @Test
    void findById() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.ofNullable(returnUser));
        User user = userService.findById(ID);
        assertNotNull(user);
        assertEquals(returnUser,user);
    }

    @Test
    void findAll() {
        User user1 =  User.builder().firstName("User1").id(1L).build();
        User user2 = User.builder().firstName("User2").id(2L).build();
        when(userRepository.findAll()).thenReturn(List.of(user1,user2));

        List<User> users = userService.findAll().stream().toList();

        assertNotNull(users);
        assertEquals(2,users.size());
        assertTrue(users.containsAll(List.of(user1,user2)));
    }

    @Test
    void deleteById() {
        userService.deleteById(ID);
        verify(userRepository,times(1)).deleteById(ID);
    }

    @Test
    void delete() {
        userService.delete(returnUser);
        verify(userRepository,times(1)).delete(returnUser);
    }

    @Test
    void findUserByEmail() {
        when(userRepository.findByEmail(anyString())).thenReturn(returnUser);
        User user = userService.findUserByEmail("Email");
        assertNotNull(user);
        assertEquals(returnUser,user);
    }

    @Test
    void findUserByUsername() {
        when(userRepository.findByUsername(anyString())).thenReturn(returnUser);
        User user = userService.findUserByUsername("Username");
        assertNotNull(user);
        assertEquals(returnUser,user);
    }

    @Test
    void findUserByLastname() {
        when(userRepository.findByLastName(anyString())).thenReturn(returnUser);
        User user = userService.findUserByLastname("Lastname");
        assertNotNull(user);
        assertEquals(returnUser,user);
    }




}