package com.propensi.genius.rest.restservice;

import java.util.List;

import com.propensi.genius.rest.restdto.request.AddAcaraBDRequestDTO;
import com.propensi.genius.rest.restdto.response.AcaraBDResponseDTO;

public interface AcaraBDRestService {
    List<AcaraBDResponseDTO> getAllAcaraBD();
    AcaraBDResponseDTO addAcaraBD(AddAcaraBDRequestDTO requestDTO);
    AcaraBDResponseDTO getAcaraBDById(String idAcaraBD);
    AcaraBDResponseDTO cancelAcaraBD(String idAcaraBD);
}
