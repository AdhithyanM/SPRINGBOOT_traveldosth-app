package com.traveldosth.service.impl;

import com.traveldosth.model.Booking;
import com.traveldosth.model.Car;
import com.traveldosth.model.Location;
import com.traveldosth.model.enums.BookingStateEnum;
import com.traveldosth.repository.BookingRepository;
import com.traveldosth.repository.LocationRepository;
import com.traveldosth.service.BookingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private LocationRepository locationRepository;

    @Override
    public Booking bookCar(Long userId, Car car, Location startLocation, Location endLocation) {
        startLocation = locationRepository.save(startLocation);
        endLocation = locationRepository.save(endLocation);
        Booking booking = new Booking(userId, car.getCarId(), car.getDriverId(), startLocation.getLocationId(), endLocation.getLocationId());
        booking = bookingRepository.save(booking);
        return booking;
    }

    @Override
    public Booking findBookingById(Long bookingId) {
        Optional<Booking> booking = bookingRepository.findById(bookingId);
        return booking.isPresent() ? booking.get() : null;
    }

    @Override
    public void cancelBooking(Booking booking) {
        booking.setBookingStatus(BookingStateEnum.CANCELLED);
        bookingRepository.save(booking);
    }
}
