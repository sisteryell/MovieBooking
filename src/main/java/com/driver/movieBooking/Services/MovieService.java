package com.driver.movieBooking.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.driver.movieBooking.Dtos.RequestDtos.MovieEntryDto;
import com.driver.movieBooking.Dtos.ResponseDtos.BulkAddMoviesResponseDto;
import com.driver.movieBooking.Dtos.ResponseDtos.BulkDeleteMoviesResponseDto;
import com.driver.movieBooking.Exceptions.MovieAlreadyPresentWithSameNameAndLanguage;
import com.driver.movieBooking.Exceptions.MovieDoesNotExists;
import com.driver.movieBooking.Entity.Movie;
import com.driver.movieBooking.Entity.Show;
import com.driver.movieBooking.Entity.Ticket;
import com.driver.movieBooking.Repositories.MovieRepository;
import com.driver.movieBooking.Repositories.ShowRepository;
import com.driver.movieBooking.Transformers.MovieTransformer;

@Service
public class MovieService {
    
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ShowRepository showRepository;

    public String addMovie(MovieEntryDto movieEntryDto) throws MovieAlreadyPresentWithSameNameAndLanguage {
        Optional<Movie> existingMovie =
                movieRepository.findByMovieNameAndLanguage(
                    movieEntryDto.getMovieName(),
                    movieEntryDto.getLanguage()
                );

        if(existingMovie.isPresent()) {
            throw new MovieAlreadyPresentWithSameNameAndLanguage();
        }
        Movie movie = MovieTransformer.movieDtoToMovie(movieEntryDto);
        movieRepository.save(movie);
        return "The movie has been added successfully";
    }

    public BulkAddMoviesResponseDto bulkAddMovies(List<MovieEntryDto> movieEntryDtos) {
        if (movieEntryDtos == null || movieEntryDtos.isEmpty()) {
            throw new IllegalArgumentException("Movie list cannot be null or empty");
        }
        int added = 0;
        List<String> skippedMovies = new ArrayList<>();
        for(MovieEntryDto movieEntryDto : movieEntryDtos) {
            try {
                addMovie(movieEntryDto);
                added++;
            } catch (MovieAlreadyPresentWithSameNameAndLanguage ex) {
                skippedMovies.add(movieEntryDto.getMovieName());
            }
        }
        return new BulkAddMoviesResponseDto(
            added,
            skippedMovies.size(),
            skippedMovies
        );
    }

    public Long totalCollection(Integer movieId) throws MovieDoesNotExists {
        if (movieId == null || movieId <= 0) {
            throw new IllegalArgumentException("movieId must be a positive number");
        }
        Optional<Movie> movieOpt = movieRepository.findById(movieId);
        if(movieOpt.isEmpty()) {
            throw new MovieDoesNotExists();
        }
        List<Show> showListOfMovie = showRepository.getAllShowsOfMovie(movieId);
        long amount = 0;
        for(Show show : showListOfMovie) {
            if (show == null || show.getTicketList() == null) {
                continue;
            }
            for(Ticket ticket : show.getTicketList()) {
                if (ticket != null) {
                    amount += (long)ticket.getTotalTicketsPrice();
                }
            }
        }
        return amount;
    }

    public String deleteMovie(Integer movieId) throws MovieDoesNotExists {
        Optional<Movie> movieOpt = movieRepository.findById(movieId);
        if(movieOpt.isEmpty()) {
            throw new MovieDoesNotExists();
        }
        movieRepository.delete(movieOpt.get());
        return "Movie deleted successfully";
    }

    public BulkDeleteMoviesResponseDto bulkDeleteMovies(List<Integer> movieIds) {
        if (movieIds == null || movieIds.isEmpty()) {
            throw new IllegalArgumentException("Movie ids list cannot be null or empty");
        }
        int deleted = 0;
        List<Integer> skipped = new ArrayList<>();
        for(Integer movieId : movieIds) {
            if (movieId == null || movieId <= 0) {
                skipped.add(movieId);
                continue;
            }
            try {
                deleteMovie(movieId);
                deleted++;
            } catch (MovieDoesNotExists ex) {
                skipped.add(movieId);
            }
        }
        return new BulkDeleteMoviesResponseDto(
            deleted,
            skipped.size(),
            skipped
        );
    }
}
