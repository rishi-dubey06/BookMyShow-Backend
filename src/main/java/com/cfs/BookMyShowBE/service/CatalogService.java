package com.cfs.BookMyShowBE.service;

import com.cfs.BookMyShowBE.dto.MovieResponse;
import com.cfs.BookMyShowBE.dto.ShowResponse;
import com.cfs.BookMyShowBE.dto.TheaterResponse;
import com.cfs.BookMyShowBE.repository.MovieRepository;
import com.cfs.BookMyShowBE.repository.ShowRepository;
import com.cfs.BookMyShowBE.repository.ShowSeatRepository;
import com.cfs.BookMyShowBE.repository.TheaterRepository;
import com.cfs.BookMyShowBE.entity.Show;



import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class CatalogService {
    private final MovieRepository movieRepository;
    private final TheaterRepository theaterRepository;
    private final ShowSeatRepository showSeatRepository;
    private final ShowRepository showRepository;

    public CatalogService(MovieRepository movieRepository, TheaterRepository theaterRepository, ShowSeatRepository showSeatRepository, ShowRepository showRepository) {
        this.movieRepository = movieRepository;
        this.theaterRepository = theaterRepository;
        this.showSeatRepository = showSeatRepository;
        this.showRepository = showRepository;
    }

    public List<MovieResponse> movies(){
        return movieRepository.findByActiveTrueOrderByTitle().stream().map(MovieResponse::from).toList();
    }
    public List<TheaterResponse> theaters(String city){
        return theaterRepository.findByCityIgnoreCaseOrderByName(city).stream().map(TheaterResponse::from).toList();
    }

    public List<ShowResponse> shows(String city, LocalDate date){
        LocalDateTime from=date.atStartOfDay();
        return  showRepository.findActiveShows(city,from,from.plusDays(1)).stream()
                .map(show -> ShowResponse.from(show,showSeatRepository.findAvailableLabels(show.getId()))).toList();
    }


}
