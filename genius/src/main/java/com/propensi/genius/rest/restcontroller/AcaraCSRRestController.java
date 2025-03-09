package com.propensi.genius.rest.restcontroller;

import com.propensi.genius.rest.restdto.request.AddAcaraCSRRequestDTO;
import com.propensi.genius.rest.restdto.request.UpdateAcaraCSRRequestDTO;
import com.propensi.genius.rest.restdto.response.AcaraCSRResponseDTO;
import com.propensi.genius.rest.restdto.response.BaseResponseDTO;
import com.propensi.genius.rest.restservice.AcaraCSRRestService;
import java.util.Date;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/acaracsr")
public class AcaraCSRRestController {
    
    private final AcaraCSRRestService acaraCSRRestService;
    
    public AcaraCSRRestController(AcaraCSRRestService acaraCSRRestService) {
        this.acaraCSRRestService = acaraCSRRestService;
    }
    
    // Endpoint untuk mendapatkan semua data AcaraCSR
    @GetMapping
    public ResponseEntity<BaseResponseDTO<List<AcaraCSRResponseDTO>>> getAllAcaraCSR(){
        List<AcaraCSRResponseDTO> listAcara = acaraCSRRestService.getAllAcaraCSR();
        BaseResponseDTO<List<AcaraCSRResponseDTO>> response = new BaseResponseDTO<>();
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Sukses mendapatkan daftar AcaraCSR");
        response.setData(listAcara);
        response.setTimestamp(new Date());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    // Endpoint untuk menambahkan AcaraCSR baru
    @PostMapping("/add")
    public ResponseEntity<BaseResponseDTO<AcaraCSRResponseDTO>> addAcaraCSR(@RequestBody AddAcaraCSRRequestDTO requestDTO) {
        BaseResponseDTO<AcaraCSRResponseDTO> response = new BaseResponseDTO<>();
        try {
            AcaraCSRResponseDTO savedAcara = acaraCSRRestService.addAcaraCSR(requestDTO);
            response.setStatus(HttpStatus.CREATED.value());
            response.setMessage("Berhasil menambahkan AcaraCSR");
            response.setData(savedAcara);
            response.setTimestamp(new Date());
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch(Exception e) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setMessage("Terjadi error saat menambahkan AcaraCSR: " + e.getMessage());
            response.setData(null);
            response.setTimestamp(new Date());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
    
    // Endpoint untuk memperbarui AcaraCSR yang sudah ada
    @PutMapping("/update")
    public ResponseEntity<BaseResponseDTO<AcaraCSRResponseDTO>> updateAcaraCSR(@RequestBody UpdateAcaraCSRRequestDTO requestDTO) {
        AcaraCSRResponseDTO updatedAcara = acaraCSRRestService.updateAcaraCSR(requestDTO);
        BaseResponseDTO<AcaraCSRResponseDTO> response = new BaseResponseDTO<>();
        if(updatedAcara == null){
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setMessage("AcaraCSR dengan id " + requestDTO.getIdAcaraCsr() + " tidak ditemukan");
            response.setData(null);
            response.setTimestamp(new Date());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Sukses mengupdate AcaraCSR dengan id " + requestDTO.getIdAcaraCsr());
        response.setData(updatedAcara);
        response.setTimestamp(new Date());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}