package com.propensi.genius.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.propensi.genius.model.AcaraCSR;
import com.propensi.genius.repository.AcaraCSRDb;

@Service
public class AcaraCSRServiceImpl implements AcaraCSRService {

    private final AcaraCSRDb acaraCSRDb;
    
    public AcaraCSRServiceImpl(AcaraCSRDb acaraCSRDb) {
        this.acaraCSRDb = acaraCSRDb;
    }
    
    @Override
    public List<AcaraCSR> getAllAcaraCSR() {
        return acaraCSRDb.findAll();
    }
    
    @Override
    public AcaraCSR addAcaraCSR(AcaraCSR acaraCSR) {
        return acaraCSRDb.save(acaraCSR);
    }
    
    @Override
    public AcaraCSR getAcaraCSRById(String idAcaraCsr) {
        return acaraCSRDb.findById(idAcaraCsr).orElse(null);
    }
    
    @Override
    public AcaraCSR updateAcaraCSR(AcaraCSR acaraCSR) {
        return acaraCSRDb.save(acaraCSR);
    }
}
