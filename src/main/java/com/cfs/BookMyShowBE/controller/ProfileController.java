package com.cfs.BookMyShowBE.controller;


import com.cfs.BookMyShowBE.dto.BookingResponse;
import com.cfs.BookMyShowBE.dto.CreateProfileRequest;
import com.cfs.BookMyShowBE.dto.ProfileResponse;
import com.cfs.BookMyShowBE.service.BookingService;
import com.cfs.BookMyShowBE.service.ProfieService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/profiles")
@CrossOrigin(origins = "*")

public class ProfileController {

    private final ProfieService profieService;
    private final BookingService bookingService;

    public ProfileController(ProfieService profieService, BookingService bookingService) {
        this.profieService = profieService;
        this.bookingService = bookingService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProfileResponse create(@Valid @RequestBody CreateProfileRequest request){

        return profieService.create(request);
    }

    @GetMapping("/login")
    public ProfileResponse login(@RequestParam String identifier){
        return profieService.login(identifier);
    }

    @GetMapping("/{profileId}/bookings")
    public List<BookingResponse> booking(@PathVariable long profileId){
        return bookingService.findByProfileId(profileId);
    }


}
