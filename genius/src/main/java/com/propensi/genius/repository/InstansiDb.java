package com.propensi.genius.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.propensi.genius.model.Instansi;

import java.util.*;
import java.util.List;
import java.util.UUID;


@Repository
public interface InstansiDb extends JpaRepository<Instansi, String> {
    
}
