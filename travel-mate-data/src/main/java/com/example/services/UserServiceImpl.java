package com.example.services;

import com.example.model.User;
import com.example.model.authorization.EnumRole;
import com.example.model.authorization.Role;
import com.example.model.dto.UserDto;
import com.example.model.repositories.RoleRepository;
import com.example.model.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User save(User user) {

        return userRepository.save(user);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public Set<User> findAll() {
        return new HashSet<>(userRepository.findAll());
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public UserDto findUserByEmail(String email) {
        return mapToUserDto(userRepository.findByEmail(email));
    }

    @Override
    public UserDto findUserByUsername(String username) {
        return mapToUserDto(userRepository.findByUsername(username));
    }

    @Override
    public UserDto findUserByLastname(String lastname) {
        return mapToUserDto(userRepository.findByLastName(lastname));
    }

    private Role getRoleUSER() {
        Role role = roleRepository.findByName(EnumRole.USER);
        if (role == null) {
            roleRepository.save(Role.builder().name(EnumRole.USER.name()).build());
        }
        return role;
    }


    private UserDto mapToUserDto(User user) {
        return UserDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .birthDate(user.getBirthDate())
                .username(user.getUsername())
                .build();
    }

}
