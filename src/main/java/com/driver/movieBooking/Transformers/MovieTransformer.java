package com.driver.movieBooking.Transformers;

import com.driver.movieBooking.Dtos.RequestDtos.MovieEntryDto;
import com.driver.movieBooking.Entity.Movie;

public class MovieTransformer {
    
    public static Movie movieDtoToMovie(MovieEntryDto movieEntryDto) {

        return Movie.builder()
                .movieName(movieEntryDto.getMovieName())
                .duration(movieEntryDto.getDuration())
                .genre(movieEntryDto.getGenre())
                .language(movieEntryDto.getLanguage())
                .releaseDate(movieEntryDto.getReleaseDate())
                .rating(movieEntryDto.getRating())
                .build();
    }
}
