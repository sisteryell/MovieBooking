package com.driver.movieBooking.Exceptions;

public class TheaterIsNotPresentOnThisAddress extends RuntimeException {
    public TheaterIsNotPresentOnThisAddress() {
        super("Theater is not present on this address");
    }
}
