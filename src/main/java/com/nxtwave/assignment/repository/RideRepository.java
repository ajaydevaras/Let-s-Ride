package com.nxtwave.assignment.repository;

import com.nxtwave.assignment.entity.Ride;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;

public interface RideRepository extends JpaRepository<Ride, Integer>,
        PagingAndSortingRepository<Ride, Integer> {

    Page<Ride> findByFromLocationAndToLocationAndDateTime(String fromLocation,
                                                          String toLocation,
                                                          Date dateTime,
                                                          Pageable pageable);
}