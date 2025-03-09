package com.propensi.genius.rest.restservice;

import java.util.List;
import com.propensi.genius.rest.restdto.request.AddAcaraCSRRequestDTO;
import com.propensi.genius.rest.restdto.request.UpdateAcaraCSRRequestDTO;
import com.propensi.genius.rest.restdto.response.AcaraCSRResponseDTO;

public interface AcaraCSRRestService {
    List<AcaraCSRResponseDTO> getAllAcaraCSR();
    
    AcaraCSRResponseDTO addAcaraCSR(AddAcaraCSRRequestDTO requestDTO);
    
    AcaraCSRResponseDTO getAcaraCSRById(String idAcaraCsr);
    
    AcaraCSRResponseDTO updateAcaraCSR(UpdateAcaraCSRRequestDTO requestDTO);
}
