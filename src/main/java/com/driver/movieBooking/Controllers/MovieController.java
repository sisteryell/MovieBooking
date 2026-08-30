package com.driver.movieBooking.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.driver.movieBooking.Dtos.RequestDtos.MovieEntryDto;
import com.driver.movieBooking.Dtos.ResponseDtos.BulkAddMoviesResponseDto;
import com.driver.movieBooking.Dtos.ResponseDtos.BulkDeleteMoviesResponseDto;
import com.driver.movieBooking.Exceptions.MovieDoesNotExists;
import com.driver.movieBooking.Services.MovieService;

@RestController
@RequestMapping("/movie")
public class MovieController {
    
    @Autowired
    private MovieService movieService;

    @PostMapping("/addNew")
    public ResponseEntity<String> addMovie(@RequestBody MovieEntryDto movieEntryDto) {
        try {
            String result = movieService.addMovie(movieEntryDto);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch(IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error: " + ex.getMessage());
        }
    }

    @PostMapping("/bulk/addNew")
    public ResponseEntity<BulkAddMoviesResponseDto> bulkAddMovies(@RequestBody List<MovieEntryDto> movieEntryDtos) {
        try {
            BulkAddMoviesResponseDto result = movieService.bulkAddMovies(movieEntryDtos);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch(IllegalArgumentException ex) {
            return ResponseEntity.badRequest().build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/totalCollection/{movieId}")
    public ResponseEntity<Long> totalCollection(@PathVariable Integer movieId) {
        try {
            Long result = movieService.totalCollection(movieId);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch(MovieDoesNotExists ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete/{movieId}")
    public ResponseEntity<String> deleteMovie(@PathVariable Integer movieId) {
        try {
            String result = movieService.deleteMovie(movieId);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/bulk/delete")
    public ResponseEntity<BulkDeleteMoviesResponseDto> bulkDeleteMovie(@RequestBody List<Integer> movieIds) {
        try {
            BulkDeleteMoviesResponseDto result = movieService.bulkDeleteMovies(movieIds);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch(IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
