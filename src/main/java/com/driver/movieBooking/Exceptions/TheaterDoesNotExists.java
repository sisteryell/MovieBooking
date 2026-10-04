package com.driver.movieBooking.Exceptions;

public class TheaterDoesNotExists extends RuntimeException {
    public TheaterDoesNotExists() {
        super("Theater does not exists");
    }
}
