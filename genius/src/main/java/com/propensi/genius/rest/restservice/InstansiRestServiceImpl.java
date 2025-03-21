package com.propensi.genius.rest.restservice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.propensi.genius.model.Instansi;
import com.propensi.genius.repository.InstansiDb;
import com.propensi.genius.rest.restdto.request.AddInstansiRequestDTO;
import com.propensi.genius.rest.restdto.response.InstansiResponseDTO;
import com.propensi.genius.rest.restdto.request.UpdateInstansiRequestDTO;
import com.propensi.genius.service.InstansiService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class InstansiRestServiceImpl implements InstansiRestService{
    
    private final InstansiService instansiService;
    private final InstansiDb instansiDb;

    public InstansiRestServiceImpl(InstansiService instansiService, InstansiDb instansiDb){
        this.instansiService = instansiService;
        this.instansiDb = instansiDb;
    }

    @Override
    public List<InstansiResponseDTO> getAllInstansi(){
        var listInstansi = instansiService.getAllInstansi();

        if(listInstansi == null || listInstansi.isEmpty()){
            return new ArrayList<>();
        }

        return listInstansi.stream().map(this::instansiToInstansiResponseDTO).collect(Collectors.toList());
    }

    @Override
    public InstansiResponseDTO addInstansi(AddInstansiRequestDTO requestDTO){
        List<InstansiResponseDTO> listInstansi = getAllInstansi();

        int idNum = listInstansi.size() + 1;
        String id = String.format("INS%04d", idNum);

        var instansi = new Instansi();
        instansi.setIdInstansi(id);
        instansi.setNamaInstansi(requestDTO.getNamaInstansi());
        instansi.setNamaNarahubung(requestDTO.getNamaNarahubung());
        instansi.setNomorKontak(requestDTO.getNomorKontak());
        instansi.setAlamatInstansi(requestDTO.getAlamatInstansi());
        instansi.setJenisInstansi(requestDTO.getJenisInstansi());

        instansiDb.save(instansi);

        return instansiToInstansiResponseDTO(instansi);

    }

    @Override
    public InstansiResponseDTO getInstansiById(String idInstansi){
        var instansi = instansiService.getInstansiById(idInstansi);

        if(instansi == null){
            return null;
        }

        return instansiToInstansiResponseDTO(instansi);
    }

    @Override
    public InstansiResponseDTO updateInstansi(UpdateInstansiRequestDTO requestDTO){
        var instansi = instansiService.getInstansiById(requestDTO.getIdInstansi());

        if(instansi == null){
            return null;
        }

        instansi.setNamaInstansi(requestDTO.getNamaInstansi());
        instansi.setNamaNarahubung(requestDTO.getNamaNarahubung());
        instansi.setNomorKontak(requestDTO.getNomorKontak());
        instansi.setAlamatInstansi(requestDTO.getAlamatInstansi());
        instansi.setJenisInstansi(requestDTO.getJenisInstansi());

        instansiService.updateInstansi(instansi);

        return instansiToInstansiResponseDTO(instansi);
    }

    private InstansiResponseDTO instansiToInstansiResponseDTO(Instansi instansi){
        InstansiResponseDTO instansiResponseDTO = new InstansiResponseDTO();
        instansiResponseDTO.setIdInstansi(instansi.getIdInstansi());
        instansiResponseDTO.setNamaInstansi(instansi.getNamaInstansi());
        instansiResponseDTO.setNamaNarahubung(instansi.getNamaNarahubung());
        instansiResponseDTO.setNomorKontak(instansi.getNomorKontak());
        instansiResponseDTO.setAlamatInstansi(instansi.getAlamatInstansi());
        instansiResponseDTO.setJenisInstansi(instansi.getJenisInstansi());

        return instansiResponseDTO;
    }

    @Override
    public void deleteInstansi(String idInstansi) throws EntityNotFoundException {
        Instansi instansi = instansiDb.findById(idInstansi).orElseThrow(() -> new EntityNotFoundException("Instansi dengan ID " + idInstansi + " tidak ditemukan"));
        instansi.setDeletedAt(new Date());
        instansiDb.save(instansi);
    }

}
