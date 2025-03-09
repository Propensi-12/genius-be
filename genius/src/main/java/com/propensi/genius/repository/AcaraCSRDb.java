package com.propensi.genius.repository;

import com.propensi.genius.model.AcaraCSR;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcaraCSRDb extends JpaRepository<AcaraCSR, String> {
    // Jika perlu, tambahkan custom query di sini, misalnya:
    // List<AcaraCSR> findByStatus(String status);
}
