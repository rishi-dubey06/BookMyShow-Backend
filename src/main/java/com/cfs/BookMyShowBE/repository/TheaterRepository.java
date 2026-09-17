package com.cfs.BookMyShowBE.repository;

import com.cfs.BookMyShowBE.entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TheaterRepository extends JpaRepository<Theater, Long> {

    List<Theater> findByCityIgnoreCaseOrderByName(String city);


    Optional<Theater> findByNameAndCity(String name, String city);
}
