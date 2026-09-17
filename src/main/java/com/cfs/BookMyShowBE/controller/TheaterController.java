package com.cfs.BookMyShowBE.controller;

import com.cfs.BookMyShowBE.dto.*;
import com.cfs.BookMyShowBE.service.CatalogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/theaters")
@CrossOrigin(origins = "*")
public class TheaterController {

    private  final CatalogService catalogService;

    public TheaterController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @GetMapping
    public List<TheaterResponse> theatres(@RequestParam String city){
        return catalogService.theaters(city);
    }
}
