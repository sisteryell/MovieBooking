package com.driver.movieBooking.Exceptions;

public class MovieDoesNotExists extends RuntimeException {
    public MovieDoesNotExists() {
        super("Movie does not Exists");
    }
}
