package com.propensi.genius.rest.restservice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.propensi.genius.model.AcaraBD;
import com.propensi.genius.model.AcaraCSR;
import com.propensi.genius.model.Instansi;
import com.propensi.genius.repository.AcaraBDDb;
import com.propensi.genius.rest.restdto.request.AddAcaraBDRequestDTO;
import com.propensi.genius.rest.restdto.response.AcaraBDResponseDTO;
import com.propensi.genius.rest.restdto.response.AcaraCSRResponseDTO;
import com.propensi.genius.rest.restdto.response.InstansiResponseDTO;
import com.propensi.genius.service.AcaraBDService;

@Service
public class AcaraBDRestServiceImpl implements AcaraBDRestService{
    
    private final AcaraBDService acaraBDService;
    private final AcaraBDDb acaraBDDb;

    public AcaraBDRestServiceImpl(AcaraBDService acaraBDService, AcaraBDDb acaraBDDb){
        this.acaraBDService = acaraBDService;
        this.acaraBDDb = acaraBDDb;
    }

    @Override
    public List<AcaraBDResponseDTO> getAllAcaraBD(){
        var listAcaraBD = acaraBDService.getAllAcaraBD();

        if(listAcaraBD == null || listAcaraBD.isEmpty()){
            return new ArrayList<>();
        }

        return listAcaraBD.stream()
            .map(this::acaraBDToAcaraBDResponseDTO)
            .collect(Collectors.toList());

    }

    @Override
    public AcaraBDResponseDTO addAcaraBD(AddAcaraBDRequestDTO requestDTO){
        List<AcaraBDResponseDTO> listAcaraBD = getAllAcaraBD();

        int idNum = listAcaraBD.size() + 1;
        String status = "Planned";
        String id = String.format("BD%04d", idNum);

        var acaraBD = new AcaraBD();
        acaraBD.setIdAcaraBD(id);
        acaraBD.setNamaAcaraBD(requestDTO.getNamaAcaraBD());
        acaraBD.setTanggalAcaraBD(requestDTO.getTanggalAcaraBD());
        acaraBD.setAnggaranBD(requestDTO.getAnggaranBD());
        acaraBD.setStatus(status);

        acaraBDDb.save(acaraBD);

        return acaraBDToAcaraBDResponseDTO(acaraBD);
    }

    @Override
    public AcaraBDResponseDTO getAcaraBDById(String idAcaraBD) {
        AcaraBD acara = acaraBDDb.findById(idAcaraBD).orElse(null);
        if(acara == null) {
            return null;
        }
        return acaraBDToAcaraBDResponseDTO(acara);
    }

    private AcaraBDResponseDTO acaraBDToAcaraBDResponseDTO(AcaraBD acaraBD) {
        AcaraBDResponseDTO acaraBDResponseDTO = new AcaraBDResponseDTO();
        acaraBDResponseDTO.setIdAcaraBD(acaraBD.getIdAcaraBD());
        acaraBDResponseDTO.setNamaAcaraBD(acaraBD.getNamaAcaraBD());
        acaraBDResponseDTO.setTanggalAcaraBD(acaraBD.getTanggalAcaraBD());
        acaraBDResponseDTO.setAnggaranBD(acaraBD.getAnggaranBD());
        acaraBDResponseDTO.setStatus(acaraBD.getStatus());

        return acaraBDResponseDTO;
    }

    @Override
    public AcaraBDResponseDTO cancelAcaraBD(String idAcaraBD) {
        AcaraBD acaraBD = acaraBDDb.findById(idAcaraBD).orElse(null);
        if (acaraBD == null) {
            return null;
        }

        acaraBD.setStatus("Cancelled");
        acaraBDDb.save(acaraBD);

        return acaraBDToAcaraBDResponseDTO(acaraBD);
    }
}
