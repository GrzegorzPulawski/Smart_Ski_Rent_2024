package com.smart_ski_rent_ver1_2.security.entity;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "authorities")
public class Authority implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String authority;

    @Override
    public String getAuthority() {
        return authority;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Authority authority1 = (Authority) o;
        return Objects.equals(id, authority1.id) && Objects.equals(authority, authority1.authority);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, authority);
    }


}
