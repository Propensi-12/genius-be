package com.propensi.genius.config;

import com.propensi.genius.model.EndUser;
import com.propensi.genius.repository.EndUserDb;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner initDatabase(EndUserDb userDb, BCryptPasswordEncoder passwordEncoder) {
        return args -> {
            String adminEmail = "admin@example.com";
            String defaultPassword = "Admin123!"; // Pastikan nanti diubah untuk keamanan

            // Cek apakah admin sudah ada di database
            if (userDb.findByEmail(adminEmail) == null) {
                EndUser admin = new EndUser();
                admin.setEmail(adminEmail);
                admin.setPassword(passwordEncoder.encode(defaultPassword)); // Hash password
                admin.setRole("ADMIN");
                admin.setDisplayName("Admin User");
                admin.setName("Administrator");
                admin.setNomorHp("08123456789");
                admin.setAlamat("Jl. Contoh No. 123");
                admin.setDeleted(false); // User aktif
                admin.setFirstLogin(true); // Set supaya wajib ganti password saat pertama login

                userDb.save(admin);
                System.out.println("Admin berhasil ditambahkan.");
            } else {
                System.out.println("Admin sudah ada di database, tidak perlu ditambahkan lagi.");
            }
        };
    }
}
