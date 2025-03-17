package com.propensi.genius.service;

import java.util.List;

import com.propensi.genius.model.AcaraBD;

public interface AcaraBDService {
    public List<AcaraBD> getAllAcaraBD();
    public AcaraBD addAcaraBD(AcaraBD acaraBD);
    public AcaraBD getAcaraBDById(String idAcaraBD);
}
