package com.propensi.genius.rest.restcontroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.propensi.genius.rest.restdto.request.UpdateProfileRequestDTO;
import com.propensi.genius.rest.restdto.response.BaseResponseDTO;
import com.propensi.genius.rest.restdto.response.UserResponseDTO;
import com.propensi.genius.rest.restservice.ProfileRestService;

import java.net.http.HttpClient;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/profile")
public class UserRestController {
    
    private final ProfileRestService profileRestService;

    public UserRestController(ProfileRestService profileRestService){
        this.profileRestService = profileRestService;
    }

    @GetMapping("/details")
    public ResponseEntity<BaseResponseDTO<UserResponseDTO>> getProfile(){

        UserResponseDTO details = profileRestService.getProfileDetails();
        var response = new BaseResponseDTO<UserResponseDTO>();

        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Sukses mendapatkan detail profile");
        response.setData(details);
        response.setTimestamp(new Date());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<BaseResponseDTO<UserResponseDTO>> updateProfile(@RequestBody UpdateProfileRequestDTO updateDTO) {
        UserResponseDTO updatedUser = profileRestService.updateProfileDetails(updateDTO);
        var response = new BaseResponseDTO<UserResponseDTO>();

        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Profil berhasil diperbarui");
        response.setData(updatedUser);
        response.setTimestamp(new Date());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    
}
