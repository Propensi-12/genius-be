package com.propensi.genius.rest.restservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.propensi.genius.model.EndUser;
import com.propensi.genius.rest.restdto.response.UserResponseDTO;
import com.propensi.genius.security.jwt.JwtUtils;
import com.propensi.genius.security.service.UserDetailsServiceImpl;
import com.propensi.genius.service.EndUserService;

@Service
public class ProfileRestServiceImpl implements ProfileRestService {
    
    @Autowired
    private EndUserService endUserService;

    @Autowired
    private JwtUtils jwtUtils;


    @Override
    public UserResponseDTO getProfileDetails() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        if (principal instanceof UserDetails userDetails) {
            String email = userDetails.getUsername();  

            EndUser user = endUserService.getUserByEmail(email);

            return new UserResponseDTO(
                user.getId(),
                user.getEmail(),
                user.getRole(),
                user.getDisplayName(),
                user.getName(),
                user.getNomorHp(),
                user.getAlamat(),
                user.isFirstLogin()
            );
        } else {
            throw new IllegalStateException("Tidak dapat menemukan pengguna dalam konteks keamanan.");
        }
    }

}