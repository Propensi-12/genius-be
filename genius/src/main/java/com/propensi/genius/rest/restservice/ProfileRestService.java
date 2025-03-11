package com.propensi.genius.rest.restservice;

import com.propensi.genius.rest.restdto.request.UpdateProfileRequestDTO;
import com.propensi.genius.rest.restdto.response.UserResponseDTO;

public interface ProfileRestService {
    UserResponseDTO getProfileDetails();
    UserResponseDTO updateProfileDetails(UpdateProfileRequestDTO updateProfileRequestDTO);
}
