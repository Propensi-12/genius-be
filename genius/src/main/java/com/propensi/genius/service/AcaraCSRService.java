package com.propensi.genius.service;

import java.util.List;
import com.propensi.genius.model.AcaraCSR;

public interface AcaraCSRService {
    List<AcaraCSR> getAllAcaraCSR();
    AcaraCSR addAcaraCSR(AcaraCSR acaraCSR);
    AcaraCSR getAcaraCSRById(String idAcaraCsr);
    AcaraCSR updateAcaraCSR(AcaraCSR acaraCSR);
}
