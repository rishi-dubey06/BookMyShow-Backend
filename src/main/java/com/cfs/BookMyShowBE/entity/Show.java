package com.cfs.BookMyShowBE.entity;


import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="shows")
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    private Theater theater;


    private LocalDateTime startsAt;

    private LocalDateTime endsAt;

    private BigDecimal ticketPrice;

    private int totalSeats;

    private int availableSeats;

    private boolean active=true;

    @Version //enable optimistic locking in JPA. It prevents two request from silently overwritting each other
    private long version;

    public Show() {
    }

    public Show(Movie movie, Theater theater, LocalDateTime startsAt, LocalDateTime endsAt, int totalSeats, BigDecimal ticketPrice) {
        this.movie = movie;
        this.theater = theater;
        this.startsAt = startsAt;
        this.endsAt = endsAt;
        this.totalSeats = totalSeats;
        this.ticketPrice = ticketPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Theater getTheater() {
        return theater;
    }

    public void setTheater(Theater theater) {
        this.theater = theater;
    }

    public LocalDateTime getStartsAt() {
        return startsAt;
    }

    public void setStartsAt(LocalDateTime startsAt) {
        this.startsAt = startsAt;
    }

    public LocalDateTime getEndsAt() {
        return endsAt;
    }

    public void setEndsAt(LocalDateTime endsAt) {
        this.endsAt = endsAt;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public void reserve(int seats){
        if(seats<=0 || seats> availableSeats){
            throw new IllegalArgumentException("Not enough Seat Available");
        }
        availableSeats-=seats;
    }

    public void release(int seats){
        availableSeats=availableSeats+seats;
    }
}
