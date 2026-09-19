package com.driver.movieBooking.Transformers;

import com.driver.movieBooking.Dtos.RequestDtos.TheaterEntryDto;
import com.driver.movieBooking.Entity.Theater;

public class TheaterTransformer {

    public static Theater theaterDtoToTreater(TheaterEntryDto theaterEntryDto) {
        Theater theater = Theater.builder()
                .name(theaterEntryDto.getName())
                .address(theaterEntryDto.getAddress())
                .build();
        return theater;
    }
}
