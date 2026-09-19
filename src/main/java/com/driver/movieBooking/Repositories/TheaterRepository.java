package com.driver.movieBooking.Repositories;

import com.driver.movieBooking.Entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TheaterRepository extends JpaRepository<Theater, Integer> {
    Optional<Theater> findByAddress(String address);
}
