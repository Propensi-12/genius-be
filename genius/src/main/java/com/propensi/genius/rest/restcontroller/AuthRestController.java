package com.propensi.genius.rest.restcontroller;

import com.propensi.genius.security.jwt.JwtUtils;
import com.propensi.genius.service.EndUserService;
import com.propensi.genius.model.EndUser;
import com.propensi.genius.repository.EndUserDb;
import com.propensi.genius.rest.restdto.request.CreateUserRequestDTO;
import com.propensi.genius.rest.restdto.request.LoginJwtRequestDTO;
import com.propensi.genius.rest.restdto.response.UserResponseDTO;

import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthRestController {

    @Autowired
    @Lazy
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private EndUserDb endUserDb;

    @Autowired
    private EndUserService endUserService;


    // Login endpoint
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginJwtRequestDTO loginRequest) {
        try {
            // Ambil user dari database berdasarkan username
            EndUser user = endUserDb.findByEmail(loginRequest.getEmail());
            if (user == null) {
                throw new BadCredentialsException("Username tidak ditemukan!");
            }

            // Authenticate user
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    user.getEmail(),
                    loginRequest.getPassword()
                )
            );

            // Ambil role dan id pengguna
            String role = user.getRole();
            String id = user.getId().toString();

            // Generate JWT Token
            String token = jwtUtils.generateJwtToken(authentication.getName(), role, id);

            // Buat User DTO dari entity `EndUser`
            UserResponseDTO userResponse = new UserResponseDTO(
                user.getId(),
                user.getEmail(),
                user.getRole(),
                user.getDisplayName(),
                user.getName(),
                user.getNomorHp(),
                user.getAlamat(),
                user.isFirstLogin()
            );
            // Create success response
            Map<String, Object> response = new HashMap<>();
            response.put("status", HttpStatus.OK.value());
            response.put("message", "Login berhasil!");
            response.put("timestamp", new Date());

            Map<String, Object> data = new HashMap<>();
            data.put("token", token);
            data.put("user", userResponse);
            response.put("data", data);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (BadCredentialsException e) {
            // Handle authentication failure
            Map<String, Object> response = new HashMap<>();
            response.put("status", HttpStatus.UNAUTHORIZED.value());
            response.put("message", "Email atau Password salah!");
            response.put("timestamp", new Date());
            response.put("data", null);

            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }

    // Register endpoint
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestHeader("Authorization") String token, @RequestBody CreateUserRequestDTO createUserRequestDTO) {
        try {
            if (token == null || !token.startsWith("Bearer ")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token tidak valid!");
            }

            token = token.substring(7); // Hapus "Bearer " dari token

            // Ambil role dari token
            String role = jwtUtils.getRoleFromJwtToken(token);

            // Hanya admin yang boleh register user baru
            if (!"ADMIN".equals(role)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Hanya admin yang bisa mendaftarkan pengguna baru!");
            }

            // Buat user baru dengan password sementara
            String temporaryPassword = endUserService.createUser(createUserRequestDTO);
            
            // Kirim email dengan kredensial awal
            // emailService.sendInitialCredentials(createUserRequestDTO.getEmail(), temporaryPassword);
            
            // Menyiapkan response jika user berhasil ditambahkan
            Map<String, Object> response = new HashMap<>();
            response.put("status", HttpStatus.CREATED.value());
            response.put("message", "Registrasi berhasil!");
            response.put("timestamp", new Date());
            response.put("data", temporaryPassword);

            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (Exception e) {
            // Menyiapkan response jika terjadi error
            Map<String, Object> response = new HashMap<>();
            response.put("status", HttpStatus.BAD_REQUEST.value());
            response.put("message", e.getMessage());
            response.put("timestamp", new Date());
            response.put("data", null);

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
