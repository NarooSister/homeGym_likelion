package com.example.homeGym.auth.dto;

import com.example.homeGym.user.entity.User;
import jakarta.persistence.metamodel.Metamodel;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CustomUserDetails implements UserDetails {

    private Long id;
    @Setter
    private String name;
    @Setter
    private String password;
    @Setter
    private String profileImageUrl;
    @Setter
    private String gender;
    @Setter
    private String email;
    @Setter
    private String birthyear;
    @Setter
    private String birthday;
    @Setter
    private String roles;
    @Setter
    private String state;
    @Setter
    private LocalDateTime createdAt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        String[] rawAuthorities = roles.split(",");
        for (String rawAuthority : rawAuthorities){
            grantedAuthorities.add(new SimpleGrantedAuthority(rawAuthority));
        }
        return grantedAuthorities;
    }

    public static CustomUserDetails fromEntity(User entity) {
        CustomUserDetails userDetails = new CustomUserDetails();

        userDetails.setName(entity.getName());
        userDetails.setPassword(entity.getPassword());
        userDetails.setProfileImageUrl(entity.getProfileImageUrl());
        userDetails.setGender(entity.getGender());
        userDetails.setEmail(entity.getEmail());
        userDetails.setBirthyear(entity.getBirthyear());
        userDetails.setBirthday(entity.getBirthday());
        userDetails.setRoles(entity.getRoles());
        userDetails.setState(String.valueOf(entity.getState()));
        userDetails.setCreatedAt(entity.getCreatedAt());
        return userDetails;
    }

    @Override
    public String getPassword() {return this.password;}

    @Override
    public String getUsername() {return this.name;}

    @Override
    public boolean isAccountNonExpired() {return true;}

    @Override
    public boolean isAccountNonLocked() {return true;}

    @Override
    public boolean isCredentialsNonExpired() {return true;}

    @Override
    public boolean isEnabled() {return true;}
}