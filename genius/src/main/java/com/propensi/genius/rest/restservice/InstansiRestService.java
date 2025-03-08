package com.propensi.genius.rest.restservice;

import java.util.List;

import com.propensi.genius.rest.restdto.request.AddInstansiRequestDTO;
import com.propensi.genius.rest.restdto.response.InstansiResponseDTO;

public interface InstansiRestService {
    List<InstansiResponseDTO> getAllInstansi();

    InstansiResponseDTO addInstansi(AddInstansiRequestDTO requestDTO);
}
