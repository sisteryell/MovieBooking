package com.driver.movieBooking.Exceptions;

public class MovieAlreadyPresentWithSameNameAndLanguage extends RuntimeException{
    public MovieAlreadyPresentWithSameNameAndLanguage() {
        super("Movie is already present with same name and language");
    }
}
