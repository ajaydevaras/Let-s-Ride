package com.nxtwave.assignment.controller;

import com.nxtwave.assignment.entity.AssetTransportationRequest;
import com.nxtwave.assignment.entity.Ride;
import com.nxtwave.assignment.model.GetAllRequests;
import com.nxtwave.assignment.model.MatchingRequest;
import com.nxtwave.assignment.service.AssetTransportationRequestService;
import com.nxtwave.assignment.service.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import static com.nxtwave.assignment.validations.AssetTransportationRequestValidations.isValidAssetSensitivity;
import static com.nxtwave.assignment.validations.AssetTransportationRequestValidations.isValidAssetType;

/**
 * Asset Controller class for handling below 3 operations.
 * 1. Add asset transportation request.
 * 2. Fetch all transportation requests.
 * 3. Get Matching Transportation Requests
 */
@RestController
public class AssetTransportationRequestController {

    @Autowired
    AssetTransportationRequestService service;

    @Autowired
    RideService rideService;

    /**
     * Adding a transportation request (Requester)
     *
     * @param assetTransportationRequest
     * @return
     */
    @PostMapping("/addAssetTransportationRequest")
    public ResponseEntity<AssetTransportationRequest> addAssetTransportationRequest(
            @RequestBody AssetTransportationRequest assetTransportationRequest) {

        // check for validations of assetType and assetSensitivity
        if (!isValidAssetType(assetTransportationRequest.getAssetType()) ||
                !isValidAssetSensitivity(assetTransportationRequest.getAssetSensitivity())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // save asset transportation request to table.
        return new ResponseEntity<AssetTransportationRequest>(
                service.saveAssetTransportationRequest(
                        assetTransportationRequest),
                HttpStatus.OK);
    }

    /**
     * Get all transportation requests information.
     *
     * @param requests
     * @return
     */
    @GetMapping("/getAllTransportationRequests")
    public ResponseEntity<Page<AssetTransportationRequest>> getAssetTransportationRequests(
            @RequestBody GetAllRequests requests) {

        // Get all transportation requests.
        return new ResponseEntity<Page<AssetTransportationRequest>>(
                service.getAllRequestsInfo(requests),
                HttpStatus.OK);
    }

    /**
     * Get Matching Riders information for a given asset transportation request.
     *
     * @param requests
     * @return
     */
    @GetMapping("/getMatchingRidesInfo")
    public ResponseEntity<Page<Ride>> getMatchingInfo(
            @RequestBody MatchingRequest requests) {

        // Get all matching rides for a given transportation request.
        AssetTransportationRequest info = service.getMatchingRequest(requests);
        Page<Ride> rideList = rideService.getRidersInfoPage(
                info,
                requests);
        return new ResponseEntity<Page<Ride>>(
                rideList,
                HttpStatus.OK);
    }

}