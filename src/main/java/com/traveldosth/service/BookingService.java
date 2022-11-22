package com.traveldosth.service;

import com.traveldosth.model.Booking;
import com.traveldosth.model.Car;
import com.traveldosth.model.Location;

public interface BookingService {

    Booking bookCar(Long userId, Car car, Location startLocation, Location endLocation);

    Booking findBookingById(Long bookingId);

    void cancelBooking(Booking booking);

}
