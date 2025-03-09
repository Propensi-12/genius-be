package com.propensi.genius.rest.restservice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.propensi.genius.model.AcaraCSR;
import com.propensi.genius.model.Instansi;
import com.propensi.genius.repository.AcaraCSRDb;
import com.propensi.genius.repository.InstansiDb;
import com.propensi.genius.rest.restdto.request.AddAcaraCSRRequestDTO;
import com.propensi.genius.rest.restdto.request.UpdateAcaraCSRRequestDTO;
import com.propensi.genius.rest.restdto.response.AcaraCSRResponseDTO;

@Service
public class AcaraCSRRestServiceImpl implements AcaraCSRRestService {
    
    private final AcaraCSRDb acaraCSRDb;
    private final InstansiDb instansiDb;
    
    public AcaraCSRRestServiceImpl(AcaraCSRDb acaraCSRDb, InstansiDb instansiDb) {
        this.acaraCSRDb = acaraCSRDb;
        this.instansiDb = instansiDb;
    }
    
    @Override
    public List<AcaraCSRResponseDTO> getAllAcaraCSR() {
        List<AcaraCSR> listAcara = acaraCSRDb.findAll();
        if(listAcara == null || listAcara.isEmpty()){
            return new ArrayList<>();
        }
        return listAcara.stream()
                        .map(this::acaraCSRToResponseDTO)
                        .collect(Collectors.toList());
    }
    
    @Override
    public AcaraCSRResponseDTO addAcaraCSR(AddAcaraCSRRequestDTO requestDTO) {
        // Generate ID baru berdasarkan jumlah data yang ada
        List<AcaraCSRResponseDTO> listAcara = getAllAcaraCSR();
        int idNum = listAcara.size() + 1;
        String id = String.format("ACR%04d", idNum);
        
        // Ambil objek Instansi berdasarkan idInstansi dari DTO
        Instansi instansi = instansiDb.findById(requestDTO.getIdInstansi()).orElse(null);
        if(instansi == null) {
            throw new RuntimeException("Instansi dengan id " + requestDTO.getIdInstansi() + " tidak ditemukan");
        }
        
        AcaraCSR acara = new AcaraCSR();
        acara.setIdAcaraCsr(id);
        acara.setInstansi(instansi);
        acara.setNamaAcaraCsr(requestDTO.getNamaAcaraCsr());
        acara.setTanggalAcaraCsr(requestDTO.getTanggalAcaraCsr());
        acara.setAnggaranCsr(requestDTO.getAnggaranCsr());
        acara.setStatus(requestDTO.getStatus());
        
        acaraCSRDb.save(acara);
        return acaraCSRToResponseDTO(acara);
    }
    
    @Override
    public AcaraCSRResponseDTO getAcaraCSRById(String idAcaraCsr) {
        AcaraCSR acara = acaraCSRDb.findById(idAcaraCsr).orElse(null);
        if(acara == null) {
            return null;
        }
        return acaraCSRToResponseDTO(acara);
    }
    
    @Override
    public AcaraCSRResponseDTO updateAcaraCSR(UpdateAcaraCSRRequestDTO requestDTO) {
        AcaraCSR acara = acaraCSRDb.findById(requestDTO.getIdAcaraCsr()).orElse(null);
        if(acara == null) {
            return null;
        }
        // Jika terdapat perubahan pada instansi, lakukan update
        if(requestDTO.getIdInstansi() != null && !requestDTO.getIdInstansi().isEmpty()){
            Instansi instansi = instansiDb.findById(requestDTO.getIdInstansi()).orElse(null);
            if(instansi != null) {
                acara.setInstansi(instansi);
            }
        }
        acara.setNamaAcaraCsr(requestDTO.getNamaAcaraCsr());
        acara.setTanggalAcaraCsr(requestDTO.getTanggalAcaraCsr());
        acara.setAnggaranCsr(requestDTO.getAnggaranCsr());
        acara.setStatus(requestDTO.getStatus());
        
        acaraCSRDb.save(acara);
        return acaraCSRToResponseDTO(acara);
    }
    
    private AcaraCSRResponseDTO acaraCSRToResponseDTO(AcaraCSR acara) {
        AcaraCSRResponseDTO responseDTO = new AcaraCSRResponseDTO();
        responseDTO.setIdAcaraCsr(acara.getIdAcaraCsr());
        responseDTO.setIdInstansi(acara.getInstansi().getIdInstansi());
        responseDTO.setNamaAcaraCsr(acara.getNamaAcaraCsr());
        responseDTO.setTanggalAcaraCsr(acara.getTanggalAcaraCsr());
        responseDTO.setAnggaranCsr(acara.getAnggaranCsr());
        responseDTO.setStatus(acara.getStatus());
        return responseDTO;
    }
}
