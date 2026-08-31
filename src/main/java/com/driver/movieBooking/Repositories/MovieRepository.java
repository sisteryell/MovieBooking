package com.driver.movieBooking.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.driver.movieBooking.Enums.Language;
import com.driver.movieBooking.Entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Integer>{
    Optional<Movie> findByMovieNameAndLanguage(String name, Language language);
}
