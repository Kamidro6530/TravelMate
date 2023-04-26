package com.example.model.authorization;

import com.example.model.BaseEntity;
import com.example.model.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "roles")
public class Role extends BaseEntity {

    @Column(nullable = false,unique = true)
    private String name;

    @ManyToMany(mappedBy="roles")
    private Set<User> users;

}
