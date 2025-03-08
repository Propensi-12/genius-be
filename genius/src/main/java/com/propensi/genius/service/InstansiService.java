package com.propensi.genius.service;

import java.util.List;

import com.propensi.genius.model.Instansi;

public interface InstansiService {
    List<Instansi> getAllInstansi();
    Instansi addInstansi(Instansi instansi);
    Instansi getInstansiById(String idInstansi);
    Instansi updateInstansi(Instansi instansi);
}
