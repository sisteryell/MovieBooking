package com.driver.movieBooking.Dtos.RequestDtos;

import lombok.Data;

@Data
public class TheaterSeatEntryDto {
    private String address;
    private Integer noOfSeatsInRow;
    private Integer noOfPremiumSeats;
    private Integer noOfClassicSeats;
}
