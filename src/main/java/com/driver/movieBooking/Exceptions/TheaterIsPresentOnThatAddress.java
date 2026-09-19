package com.driver.movieBooking.Exceptions;

public class TheaterIsPresentOnThatAddress extends RuntimeException {
    public TheaterIsPresentOnThatAddress() {
        super("Theater is already present on this address");
    }
}
