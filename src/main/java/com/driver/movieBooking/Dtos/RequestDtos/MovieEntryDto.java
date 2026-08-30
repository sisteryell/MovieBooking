package com.driver.movieBooking.Dtos.RequestDtos;

import java.sql.Date;

import com.driver.movieBooking.Enums.Genre;
import com.driver.movieBooking.Enums.Language;

import lombok.Data;

@Data
public class MovieEntryDto {
    private String movieName;
    private Integer duration;
    private Double rating;
    private Date releaseDate;
    private Genre genre;
    private Language language;
}
