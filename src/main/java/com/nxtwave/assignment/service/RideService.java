package com.nxtwave.assignment.service;

import com.nxtwave.assignment.entity.AssetTransportationRequest;
import com.nxtwave.assignment.entity.Ride;
import com.nxtwave.assignment.model.MatchingRequest;
import com.nxtwave.assignment.repository.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RideService {

    @Autowired
    RideRepository rideRepository;

    /**
     * Save ride info to the table.
     * @param ride
     * @return
     */
    public Ride saveRide(Ride ride) {
        return rideRepository.save(ride);
    }

    public Ride findById(int id) {
        return rideRepository.findOne(id);
    }

    public Ride updateRide(MatchingRequest request) {
        Ride oldRide = rideRepository.findOne(request.getRiderId());
        oldRide.setStatus(Ride.RideStatus.APPLIED.name());
        return rideRepository.save(oldRide);
    }

    public List<Ride> findAll() {
        return rideRepository.findAll();
    }

    public Page<Ride> getRidersInfoPage(AssetTransportationRequest info,
                                        MatchingRequest matchingRequests) {
        PageRequest pageRequest = new PageRequest(
                matchingRequests.getOffset(),
                matchingRequests.getPageSize());
        return rideRepository.findByFromLocationAndToLocationAndDateTime(
                info.getFromLocation(),
                info.getToLocation(),
                info.getDateTime(),
                pageRequest);
    }
}