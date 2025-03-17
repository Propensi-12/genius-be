package com.propensi.genius.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.propensi.genius.model.AcaraBD;
import com.propensi.genius.repository.AcaraBDDb;

@Service
public class AcaraBDServiceImpl implements AcaraBDService {

    private final AcaraBDDb acaraBDDb;

    public AcaraBDServiceImpl(AcaraBDDb acaraBDDb){
        this.acaraBDDb = acaraBDDb;
    }

    @Override
    public List<AcaraBD> getAllAcaraBD(){
        return acaraBDDb.findAll();
    }

    @Override
    public AcaraBD getAcaraBDById(String idAcaraBD) {
        return acaraBDDb.findById(idAcaraBD).orElse(null);
    }

    @Override
    public AcaraBD addAcaraBD(AcaraBD acaraBD){
        return acaraBDDb.save(acaraBD);
    }
}
