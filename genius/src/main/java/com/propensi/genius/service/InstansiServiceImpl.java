package com.propensi.genius.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.propensi.genius.model.Instansi;
import com.propensi.genius.repository.InstansiDb;

@Service
public class InstansiServiceImpl implements InstansiService{
    
    private final InstansiDb instansiDb;

    public InstansiServiceImpl(InstansiDb instansiDb){
        this.instansiDb = instansiDb;
    }

    @Override
    public List<Instansi> getAllInstansi(){
        return instansiDb.findAll();
    }

    @Override
    public Instansi addInstansi(Instansi instansi){
        return instansiDb.save(instansi);
    }
}
