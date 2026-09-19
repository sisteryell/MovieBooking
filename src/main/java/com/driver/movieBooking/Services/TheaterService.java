package com.driver.movieBooking.Services;

import com.driver.movieBooking.Dtos.RequestDtos.TheaterEntryDto;
import com.driver.movieBooking.Dtos.RequestDtos.TheaterSeatEntryDto;
import com.driver.movieBooking.Entity.Theater;
import com.driver.movieBooking.Entity.TheaterSeat;
import com.driver.movieBooking.Enums.SeatType;
import com.driver.movieBooking.Exceptions.TheaterIsNotPresentOnThisAddress;
import com.driver.movieBooking.Exceptions.TheaterIsPresentOnThatAddress;
import com.driver.movieBooking.Repositories.TheaterRepository;
import com.driver.movieBooking.Transformers.TheaterTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TheaterService {

    @Autowired
    private TheaterRepository theaterRepository;

    public String addTheater(TheaterEntryDto theaterEntryDto) {
        if (theaterRepository.findByAddress(theaterEntryDto.getAddress()).isPresent()) {
            throw new TheaterIsPresentOnThatAddress();
        }
        Theater theater = TheaterTransformer.theaterDtoToTreater(theaterEntryDto);
        theaterRepository.save(theater);
        return "Theater has been saved successfully";
    }

    public String addTheaterSeat(TheaterSeatEntryDto theaterSeatEntryDto) {
        String address = theaterSeatEntryDto.getAddress();
        Integer noOfSeatsInRow = theaterSeatEntryDto.getNoOfSeatsInRow();
        Integer noOfPremiumSeats = theaterSeatEntryDto.getNoOfPremiumSeats();
        Integer noOfClassicSeats = theaterSeatEntryDto.getNoOfClassicSeats();

        Optional<Theater> OptionalTheater = theaterRepository.findByAddress(address);

        if (OptionalTheater.isEmpty()) {
            throw new TheaterIsNotPresentOnThisAddress();
        }

        Theater theater = OptionalTheater.get();
        List<TheaterSeat> seatList = theater.getTheaterSeatList();

        int counter = 1;
        int fill = 0;
        char ch = 'A';

        for (int i = 1; i <= noOfClassicSeats; i++) {
            String seatNo = Integer.toString(counter) + ch;
            ch++;
            fill++;
            if (fill == noOfSeatsInRow) {
                fill = 0;
                counter++;
                ch = 'A';
            }

            TheaterSeat theaterSeat = new TheaterSeat();
            theaterSeat.setSeatNo(seatNo);
            theaterSeat.setSeatType(SeatType.CLASSIC);
            theaterSeat.setTheater(theater);
            seatList.add(theaterSeat);
        }
        for (int i = 1; i <= noOfPremiumSeats; i++) {
            String seatNo = Integer.toString(counter) + ch;
            ch++;
            fill++;
            if (fill == noOfSeatsInRow) {
                fill = 0;
                counter++;
                ch = 'A';
            }

            TheaterSeat theaterSeat = new TheaterSeat();
            theaterSeat.setSeatNo(seatNo);
            theaterSeat.setSeatType(SeatType.PREMIUM);
            theaterSeat.setTheater(theater);
            seatList.add(theaterSeat);
        }

        theaterRepository.save(theater);

        return "Theater seats have been added succesfully";
    }
}
