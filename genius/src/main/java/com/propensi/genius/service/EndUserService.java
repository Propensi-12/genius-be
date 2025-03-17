package com.propensi.genius.service;

import com.propensi.genius.model.EndUser;
import com.propensi.genius.repository.EndUserDb;
import com.propensi.genius.rest.restdto.request.CreateUserRequestDTO;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EndUserService {

    @Autowired
    private EndUserDb userDb;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // ✅ Ambil semua pengguna yang tidak dihapus (is_deleted = false)
    public List<EndUser> getAllUsers() {
        return userDb.findAll();
    }

    // ✅ Ambil pengguna berdasarkan ID
    public Optional<EndUser> getUserById(UUID id) {
        return userDb.findById(id);
    }

    // ✅ Ambil pengguna berdasarkan email (untuk login)
    public EndUser getUserByEmail(String email) {
        return userDb.findByEmail(email);
    }

    // ✅ Buat pengguna baru
    @Transactional
    public String createUser(CreateUserRequestDTO createUserDTO) {
        // Periksa apakah email sudah digunakan
        if (userDb.findByEmail(createUserDTO.getEmail()) != null) {
            return "Email sudah digunakan!";
        }

        // Enkripsi password
        // Buat password sementara (random 8 karakter)
        String temporaryPassword = generateTemporaryPassword();
        String encodedPassword = passwordEncoder.encode(temporaryPassword);

        // Buat objek user baru
        EndUser user = new EndUser();
        user.setEmail(createUserDTO.getEmail());
        user.setPassword(encodedPassword);
        user.setRole(createUserDTO.getRole());
        user.setDisplayName(createUserDTO.getDisplayName());
        user.setName(createUserDTO.getName());
        user.setNomorHp(createUserDTO.getNomorHp());
        user.setAlamat(createUserDTO.getAlamat());
        user.setDeleted(false); // Set default user aktif

        // Simpan user ke database
        userDb.save(user);
        return temporaryPassword;
    }

    // ✅ Hapus pengguna (soft delete)
    public String deleteUser(UUID id) {
        Optional<EndUser> userOpt = userDb.findById(id);
        if (userOpt.isPresent()) {
            EndUser user = userOpt.get();
            user.setDeleted(true); // Tandai sebagai dihapus
            userDb.save(user);
            return "User berhasil dihapus (soft delete).";
        }
        return "User tidak ditemukan.";
    }

    private String generateTemporaryPassword() {
        return UUID.randomUUID().toString().substring(0, 8); // ⬅ Ambil 8 karakter pertama dari UUID
    }
}
