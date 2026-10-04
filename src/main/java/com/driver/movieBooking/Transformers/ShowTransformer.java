package com.driver.movieBooking.Transformers;

import com.driver.movieBooking.Dtos.RequestDtos.ShowEntryDto;
import com.driver.movieBooking.Entity.Show;

public class ShowTransformer {
    public static Show showDtoToShow(ShowEntryDto showEntryDto) {
        return Show.builder()
                .time(showEntryDto.getShowStartTime())
                .date(showEntryDto.getShowDate())
                .build();
    }
}
