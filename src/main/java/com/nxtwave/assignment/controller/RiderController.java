package com.nxtwave.assignment.controller;

import com.nxtwave.assignment.entity.Ride;
import com.nxtwave.assignment.model.MatchingRequest;
import com.nxtwave.assignment.service.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import static com.nxtwave.assignment.validations.RideValidations.isValidTravelMedium;

/**
 * Rider Controller class for handling below 3 operations.
 * 1. Add rider info.
 * 2. For an asset transportation request, update ride status for booking confirmation.
 * 3. Get all riders information.
 */
@RestController
public class RiderController {

    @Autowired
    RideService service;

    /**
     * Add ride information.
     * @param ride
     * @return
     */
    @PostMapping("/addRide")
    public ResponseEntity<Ride> addRide(@RequestBody Ride ride) {
        if (!isValidTravelMedium(ride.getTravelMedium())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Ride>(service.saveRide(ride), HttpStatus.OK);
    }

    /**
     * Update ride status to APPLIED, post requester confirms the ride.
     * @param request
     * @return
     */
    @PostMapping("/updateRideStatus")
    public ResponseEntity<Ride> updateRide(@RequestBody MatchingRequest request) {
        return new ResponseEntity<Ride>(
                service.updateRide(request),
                HttpStatus.OK);
    }

    /**
     * Get all rides information.
     * @return
     */
    @GetMapping("/getAllRides")
    public ResponseEntity<List<Ride>> getAllRides() {
        return new ResponseEntity<List<Ride>>(
                service.findAll(),
                HttpStatus.OK);
    }
}
