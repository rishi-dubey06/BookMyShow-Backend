package com.cfs.BookMyShowBE.dto;

import com.cfs.BookMyShowBE.entity.Show;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record ShowResponse(Long id, MovieResponse movie,
                           TheaterResponse theater, LocalDateTime startAt,
                           LocalDateTime endAt, BigDecimal ticketPrice,
                           int totalSeats, int availableSeats,
                           List<String> availableSeatsLabels) {

    public static ShowResponse from (Show show, List<String> availableSeatsLabels){
        return new ShowResponse(show.getId(),
                MovieResponse.from(show.getMovie()),
                TheaterResponse.from(show.getTheater()),show.getStartsAt(),
                show.getEndsAt(), show.getTicketPrice(),
                show.getTotalSeats(), show.getAvailableSeats(),
                availableSeatsLabels );
    }
}
