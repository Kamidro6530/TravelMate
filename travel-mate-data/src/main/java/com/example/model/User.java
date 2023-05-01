package com.example.model;


import com.example.model.authorization.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.validation.Errors;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(name = "username")
    private String username;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @NotBlank(message = "Email is required")
    @Column(name = "email")
    private String email;
    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    @Column(name = "password")
    private String password;
    @NotBlank(message = "Please confirm password")
    @Size(min = 6, message = "Confirm Password must be at least 6 characters long")
    @Column(name = "confirm_password")
    private String confirmPassword;
    @Column(name = "birth_date")
    private LocalDate birthDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Itineraries> itineraries = new HashSet<Itineraries>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @Builder
    public User(Long id,String username,String firstName,String lastName,
                String email,String password,String confirmPassword,LocalDate birthDate,Set<Itineraries> itineraries,Set<Role> roles){
        super(id);
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.birthDate = birthDate;
        this.itineraries = itineraries;
        this.roles = roles;

    }

    public void validatePasswords(Errors errors) {
        if (!this.password.equals(this.confirmPassword)) {
            errors.rejectValue("confirmPassword", "password.mismatch", "Passwords do not match");
        }
    }

}
