package com.driver.movieBooking.Exceptions;

public class ShowDoesNotExists extends RuntimeException {
    public ShowDoesNotExists() {
        super("Show does not exists");
    }
}
