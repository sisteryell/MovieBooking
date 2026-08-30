package com.driver.movieBooking.Dtos.ResponseDtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BulkAddMoviesResponseDto {
    private int added;
    private int skipped;
    private List<String> skippedMovies;
}
