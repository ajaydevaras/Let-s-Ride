package com.nxtwave.assignment.validations;

import java.util.Arrays;

/**
 * Validations class for Ride.
 */
public class RideValidations {

    public enum TravelMediums {
        BUS,
        CAR,
        TRAIN;
    }

    public static boolean isValidTravelMedium(String travelMedium) throws IllegalArgumentException {
       return Arrays.stream(
               TravelMediums.values())
               .map(TravelMediums::name)
               .anyMatch(travelMedium::equals);
    }
}
