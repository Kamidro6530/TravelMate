package com.example.services.user;

import com.example.model.User;
import com.example.model.authorization.EnumRole;
import com.example.model.authorization.Role;
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
     PasswordEncoder passwordEncoder;

    @InjectMocks
    UserServiceImpl userService;

    private static final String FIRST_NAME = "TEST";
    private static final Long ID = 1L;
    private static final String CODED_PASSWORD = "########";


    User returnUser;

    Role returnRole;

    @BeforeEach
    void setUp() {
        returnRole = Role.builder().name(EnumRole.USER.name()).build();
        returnUser = User.builder()
                .firstName(FIRST_NAME)
                .id(ID)
                .roles(Set.of(returnRole))
                .build();
    }

    @Test
    void save() {
        //when
        userService.save(returnUser);
        //then
        verify(userRepository,times(1)).save(any(User.class));

    }

    @Test
    void savedUserPasswordShouldBeCoded(){
        when(passwordEncoder.encode(any())).thenReturn(CODED_PASSWORD);
        returnUser.setPassword(passwordEncoder.encode("Password"));
        //then
        assertNotNull(returnUser.getPassword());
        assertEquals(CODED_PASSWORD,returnUser.getPassword());
    }

    @Test
    void findById() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.ofNullable(returnUser));
        User user = userService.findById(ID);
        //then
        assertNotNull(user);
        assertEquals(returnUser,user);
    }

    @Test
    void findAll() {
        //given
        User user1 =  User.builder().firstName("User1").id(1L).build();
        User user2 = User.builder().firstName("User2").id(2L).build();
        //when
        when(userRepository.findAll()).thenReturn(List.of(user1,user2));
        List<User> users = userService.findAll().stream().toList();
        //then
        assertNotNull(users);
        assertEquals(2,users.size());
        assertTrue(users.containsAll(List.of(user1,user2)));
    }

    @Test
    void deleteById() {
        //when
        userService.deleteById(ID);
        //then
        verify(userRepository,times(1)).deleteById(ID);
    }

    @Test
    void delete() {
        //when
        userService.delete(returnUser);
        //then
        verify(userRepository,times(1)).delete(returnUser);
    }

    @Test
    void findUserByEmail() {
        when(userRepository.findByEmail(anyString())).thenReturn(returnUser);
        User user = userService.findUserByEmail("Email");
        //then
        assertNotNull(user);
        assertEquals(returnUser,user);
    }

    @Test
    void findUserByUsername() {
        when(userRepository.findByUsername(anyString())).thenReturn(returnUser);
        User user = userService.findUserByUsername("Username");
        //then
        assertNotNull(user);
        assertEquals(returnUser,user);
    }

    @Test
    void findUserByLastname() {
        when(userRepository.findByLastName(anyString())).thenReturn(returnUser);
        User user = userService.findUserByLastname("Lastname");
        //then
        assertNotNull(user);
        assertEquals(returnUser,user);
    }




}