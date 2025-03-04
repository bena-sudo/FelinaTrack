package com.pfc.felinatrack_back.security.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.pfc.felinatrack_back.model.db.UserDb;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MainUser implements UserDetails {

    private String name;
    private String email;
    private String password;
    private String phone;


    private Collection<? extends GrantedAuthority> authorities;

    public MainUser(String name, String email, String password, String phone, Collection<? extends GrantedAuthority> authorities) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.authorities = authorities;
    }

    public static MainUser build(UserDb usuarioDb){
        List<GrantedAuthority> authorities =
                usuarioDb.getRoles().stream().map(rol -> new SimpleGrantedAuthority(rol
                .getName().name())).collect(Collectors.toList());
        return new MainUser(usuarioDb.getName(), usuarioDb.getEmail(), usuarioDb.getPassword(), usuarioDb.getPhone(), authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
