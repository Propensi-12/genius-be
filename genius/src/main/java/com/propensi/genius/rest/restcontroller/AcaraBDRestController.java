package com.propensi.genius.rest.restcontroller;

import com.propensi.genius.rest.restdto.request.AddAcaraBDRequestDTO;
import com.propensi.genius.rest.restdto.response.BaseResponseDTO;
import com.propensi.genius.rest.restdto.response.AcaraBDResponseDTO;
import com.propensi.genius.rest.restdto.response.AcaraCSRResponseDTO;
import com.propensi.genius.rest.restservice.AcaraBDRestService;

import java.util.List;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/acarabd")
public class AcaraBDRestController {

    private final AcaraBDRestService acaraBDRestService;

    public AcaraBDRestController(AcaraBDRestService acaraBDRestService){
        this.acaraBDRestService = acaraBDRestService;
    }

    @GetMapping
    public ResponseEntity<BaseResponseDTO<List<AcaraBDResponseDTO>>> getAllAcaraBD(){
        List<AcaraBDResponseDTO> listAcara = acaraBDRestService.getAllAcaraBD();
        BaseResponseDTO<List<AcaraBDResponseDTO>> response = new BaseResponseDTO<>();
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Sukses mendapatkan daftar AcaraBD");
        response.setData(listAcara);
        response.setTimestamp(new Date());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<BaseResponseDTO<AcaraBDResponseDTO>> addAcaraBD(@RequestBody AddAcaraBDRequestDTO requestDTO) {
        BaseResponseDTO<AcaraBDResponseDTO> response = new BaseResponseDTO<>();

        try{
            AcaraBDResponseDTO savedAcaraBD = acaraBDRestService.addAcaraBD(requestDTO);
            response.setStatus(HttpStatus.CREATED.value());
            response.setMessage("Berhasil menambahkan instansi");
            response.setData(savedAcaraBD);
            response.setTimestamp(new Date());
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e){
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setMessage("Terjadi error saat menambahkan acara: " + e.getMessage());
            response.setData(null);
            response.setTimestamp(new Date());

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    
    }

    @GetMapping("/{idAcaraBD}/detail")
    public ResponseEntity<?> getDetailAcaraBD(@PathVariable String idAcaraBD) {
        var baseResponseDTO = new BaseResponseDTO<AcaraBDResponseDTO>();

        AcaraBDResponseDTO acaraBD = acaraBDRestService.getAcaraBDById(idAcaraBD);

        if (acaraBD == null) {
            baseResponseDTO.setStatus(HttpStatus.NOT_FOUND.value());
            baseResponseDTO.setMessage(String.format("Data Acara BD tidak ditemukan"));
            baseResponseDTO.setTimestamp(new Date());
            return new ResponseEntity<>(baseResponseDTO, HttpStatus.NOT_FOUND);
        }

        baseResponseDTO.setStatus(HttpStatus.OK.value());
        baseResponseDTO.setData(acaraBD);
        baseResponseDTO.setMessage(String.format("Acara BD dengan ID %s berhasil ditemukan", acaraBD.getIdAcaraBD()));
        baseResponseDTO.setTimestamp(new Date());

        return new ResponseEntity<>(baseResponseDTO, HttpStatus.OK);

    }

    @PutMapping("/{idAcaraBD}/cancel")
        public ResponseEntity<BaseResponseDTO<AcaraBDResponseDTO>> cancelAcaraBD(@PathVariable String idAcaraBD) {
        var response = new BaseResponseDTO<AcaraBDResponseDTO>();

        AcaraBDResponseDTO cancelledAcara = acaraBDRestService.cancelAcaraBD(idAcaraBD);

        if (cancelledAcara == null) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setMessage(String.format("AcaraBD dengan ID %s tidak ditemukan", idAcaraBD));
            response.setData(null);
            response.setTimestamp(new Date());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        response.setStatus(HttpStatus.OK.value());
        response.setMessage(String.format("AcaraBD dengan ID %s berhasil dibatalkan", idAcaraBD));
        response.setData(cancelledAcara);
        response.setTimestamp(new Date());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

