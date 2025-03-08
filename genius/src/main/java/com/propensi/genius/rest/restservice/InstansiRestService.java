package com.propensi.genius.rest.restservice;

import java.util.List;

import com.propensi.genius.rest.restdto.request.AddInstansiRequestDTO;
import com.propensi.genius.rest.restdto.response.InstansiResponseDTO;
import com.propensi.genius.rest.restdto.request.UpdateInstansiRequestDTO;

public interface InstansiRestService {
    List<InstansiResponseDTO> getAllInstansi();

    InstansiResponseDTO addInstansi(AddInstansiRequestDTO requestDTO);
    InstansiResponseDTO getInstansiById(String idInstansi);
    InstansiResponseDTO updateInstansi(UpdateInstansiRequestDTO requestDTO);
}
