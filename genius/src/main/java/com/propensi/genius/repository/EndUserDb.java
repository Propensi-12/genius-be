package com.propensi.genius.repository;

import com.propensi.genius.model.EndUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EndUserDb extends JpaRepository<EndUser, UUID> {

    EndUser findByEmail(String email);

}
