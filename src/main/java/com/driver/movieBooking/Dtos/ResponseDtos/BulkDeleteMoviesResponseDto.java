package com.driver.movieBooking.Dtos.ResponseDtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BulkDeleteMoviesResponseDto {
    private int deleted;
    private int skipped;
    private List<Integer> moviesDoesNotExist;
}
