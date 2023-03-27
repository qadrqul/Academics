package com.example.academics.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class Role {
    @Id
    @Column(unique = true)
    private String role;

    @ManyToMany(mappedBy = "roles")
    private Set<Users> users;

    public Role() {
    }

    public Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public Role setRole(String role) {
        this.role = role;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role1 = (Role) o;
        return Objects.equals(role, role1.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(role);
    }
}
