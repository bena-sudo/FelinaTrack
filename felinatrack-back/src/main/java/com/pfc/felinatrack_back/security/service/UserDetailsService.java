package com.pfc.felinatrack_back.security.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailsService {
    
    public UserDetails loadUserByEmail(String email);
}
