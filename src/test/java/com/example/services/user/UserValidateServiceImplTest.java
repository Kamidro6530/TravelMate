package com.example.services.user;

import com.example.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.Errors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserValidateServiceImplTest {

    @Mock
    UserServiceImpl userService;

    @InjectMocks
    private UserValidateServiceImpl userValidateService;

    @Mock
    private Errors errors;

    User returnUser;

    public static final String FIRST_NAME = "TEST";
    public static final Long ID = 1L;
    public static final String PASSWORD = "Password";
    public static final String CONFIRM_PASSWORD = "Password";

    @BeforeEach
    void setUp() {
        returnUser = User.builder()
                .firstName(FIRST_NAME)
                .id(ID)
                .password(PASSWORD)
                .confirmPassword(CONFIRM_PASSWORD)
                .build();
    }

    @Test
    public void validatePasswords_validPasswords_noErrors() {
        //when
         userValidateService.validatePasswords(returnUser, errors);
         //then
        verify(errors, never()).rejectValue(anyString(), anyString(), anyString());
    }

    @Test
    public void validatePasswords_invalidPasswords_passwordMismatch() {
        // given
        returnUser.setPassword("ONE");
        returnUser.setConfirmPassword("TWO");
        // when
        userValidateService.validatePasswords(returnUser, errors);
        // then
        ArgumentCaptor<String> fieldCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> codeCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> messageCaptor = ArgumentCaptor.forClass(String.class);

        verify(errors,times(1)).rejectValue(anyString(),anyString(),anyString());
        verify(errors).rejectValue(fieldCaptor.capture(), codeCaptor.capture(), messageCaptor.capture());

        assertEquals(fieldCaptor.getValue(),"confirmPassword");
        assertEquals(codeCaptor.getValue(),"password.mismatch");
        assertEquals(messageCaptor.getValue(),"Passwords do not match");

    }

    @Test
    public void validateEmailExist_emailExists_emailAlreadyExist() {
        // given
        lenient().when(userService.findUserByEmail(any())).thenReturn(returnUser);

        // when
        userValidateService.validateEmailExist(returnUser, errors);

        // then
        ArgumentCaptor<String> fieldCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> codeCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> messageCaptor = ArgumentCaptor.forClass(String.class);
        verify(errors).rejectValue(fieldCaptor.capture(), codeCaptor.capture(), messageCaptor.capture());
        assertEquals(fieldCaptor.getValue(),"email");
        assertEquals(codeCaptor.getValue(),"email.alreadyExist");
        assertEquals(messageCaptor.getValue(),"There is an account with that email address: " + returnUser.getEmail());
        verify(errors,times(1)).rejectValue(anyString(),anyString(),anyString());

    }

    @Test
    public void validateEmailExist_emailDoesNotExist_noErrors() {
        // given
        when(userService.findUserByEmail(any())).thenReturn(null);
        // when
        userValidateService.validateEmailExist(returnUser, errors);
        // then
        verify(errors, never()).rejectValue(anyString(), anyString(), anyString());
    }
}