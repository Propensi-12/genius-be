package com.propensi.genius.rest.restcontroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.propensi.genius.rest.restdto.request.AddInstansiRequestDTO;
import com.propensi.genius.rest.restdto.response.BaseResponseDTO;
import com.propensi.genius.rest.restdto.response.InstansiResponseDTO;
import com.propensi.genius.rest.restservice.InstansiRestService;

import java.util.List;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/instansi")
public class InstansiRestController {

    private final InstansiRestService instansiRestService;

    public InstansiRestController(InstansiRestService instansiRestService){
        this.instansiRestService = instansiRestService;
    }

    @GetMapping
    public ResponseEntity<BaseResponseDTO<List<InstansiResponseDTO>>> getAllInstansi(){
        List<InstansiResponseDTO> listInstansi = instansiRestService.getAllInstansi();

        var response = new BaseResponseDTO<List<InstansiResponseDTO>>();
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Sukses mendapatkan daftar instansi");
        response.setData(listInstansi);
        response.setTimestamp(new Date());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PostMapping("/add")
    public ResponseEntity<BaseResponseDTO<InstansiResponseDTO>> addInstansi(@RequestBody AddInstansiRequestDTO requestDTO) {
        BaseResponseDTO<InstansiResponseDTO> response = new BaseResponseDTO<>();

        try{
            InstansiResponseDTO savedInstansi = instansiRestService.addInstansi(requestDTO);
            response.setStatus(HttpStatus.CREATED.value());
            response.setMessage("Berhasil menambahkan instansi");
            response.setData(savedInstansi);
            response.setTimestamp(new Date());

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e){
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setMessage("Terjadi error saat menambahkan instansi: " + e.getMessage());
            response.setData(null);
            response.setTimestamp(new Date());

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    
    }
    
    
}
