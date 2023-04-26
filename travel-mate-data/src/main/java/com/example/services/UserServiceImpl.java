package com.example.services;

import com.example.model.User;
import com.example.model.authorization.EnumRole;
import com.example.model.authorization.Role;
import com.example.model.dto.UserDto;
import com.example.model.repositories.RoleRepository;
import com.example.model.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;
import java.util.stream.Collectors;

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
    public void save(UserDto userDto) {

        Role role = getRoleUSER();

        userRepository.save(mapToUser(userDto,Set.of(role)));
    }

    @Override
    public UserDto findById(Long id) {
        return mapToUserDto(userRepository.findById(id).orElse(null));
    }

    @Override
    public Set<UserDto> findAll() {
      return userRepository.findAll()
              .stream()
              .map(this::mapToUserDto)
              .collect(Collectors.toSet());
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void delete(UserDto userDto) {
        userRepository.delete(mapToUser(userDto,Set.of(getRoleUSER())));
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

    private Role getRoleUSER(){
        Role role = roleRepository.findByName(EnumRole.USER);
        if (role == null){
            roleRepository.save(Role.builder().name(EnumRole.USER.name()).build());
        }
        return role;
    }

    private UserDto mapToUserDto(User user) {
        UserDto userDto = new UserDto();
             userDto.setFirstName(user.getFirstName());
             userDto.setLastName(user.getLastName());
             userDto.setEmail(user.getEmail());
             userDto.setBirthDate(user.getBirthDate());
             userDto.setUsername(user.getUsername());
        return userDto;
    }

    private User mapToUser(UserDto userDto,Set<Role> roles) {
       return User.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .birthDate(userDto.getBirthDate())
                .username(userDto.getUsername())
                .roles(roles)
                .build();
    }
}
