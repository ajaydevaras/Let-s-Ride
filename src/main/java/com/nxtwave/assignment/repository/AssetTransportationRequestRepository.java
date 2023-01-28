package com.nxtwave.assignment.repository;

import com.nxtwave.assignment.entity.AssetTransportationRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AssetTransportationRequestRepository extends
        JpaRepository<AssetTransportationRequest, Integer>,
        PagingAndSortingRepository<AssetTransportationRequest, Integer> {

    Page<AssetTransportationRequest> findByAssetType(String assetType, Pageable pageable);
    Page<AssetTransportationRequest> findByStatus(String status, Pageable pageable);
}
