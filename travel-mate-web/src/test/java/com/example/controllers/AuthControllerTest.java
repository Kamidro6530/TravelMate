package com.example.controllers;

import com.example.model.User;
import com.example.services.UserService;
import com.example.services.UserValidateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    @InjectMocks
    AuthController authController;

    @Mock
    UserService userService;

    @Mock
    UserValidateService userValidateService;

    @MockBean
    BindingResult bindingResult;

    MockMvc mockMvc;

    User returnUser;

    public static final String FIRST_NAME = "TEST";
    public static final Long ID = 1L;

    @BeforeEach()
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
        returnUser = User.builder()
                .firstName(FIRST_NAME)
                .id(ID)
                .build();
    }

    @Test
    void registrationForm() throws Exception {
        mockMvc.perform(get("/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("authorization/register"));
    }

    @Test
    void login() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("authorization/login"));
    }

    @Test
    public void testRegistrationNewUserWithValidData() throws Exception {
        mockMvc.perform(post("/register/save")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("email", "test@example.com")
                        .param("password", "password")
                        .param("confirmPassword", "password"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/index"));

        verify(userService).save(any(User.class));
    }
}