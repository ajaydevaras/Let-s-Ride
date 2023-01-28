package com.nxtwave.assignment.service;

import com.nxtwave.assignment.constants.ApplicationConstants;
import com.nxtwave.assignment.entity.AssetTransportationRequest;
import com.nxtwave.assignment.model.GetAllRequests;
import com.nxtwave.assignment.model.MatchingRequest;
import com.nxtwave.assignment.repository.AssetTransportationRequestRepository;
import com.nxtwave.assignment.utils.AssertTransportRequestorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class AssetTransportationRequestService {

    @Autowired
    AssetTransportationRequestRepository assetTransportationRequestRepository;

    /**
     * Save asset transportation request to table.
     * @param assetTransportationRequest
     * @return
     */
    public AssetTransportationRequest saveAssetTransportationRequest(AssetTransportationRequest assetTransportationRequest) {
        return assetTransportationRequestRepository.save(assetTransportationRequest);
    }

    public AssetTransportationRequest getMatchingRequest(MatchingRequest request) {
        return assetTransportationRequestRepository.findOne(request.getAssetTransportRequestId());
    }

    public Page<AssetTransportationRequest> getAllRequestsInfo(GetAllRequests requests) {
        Page<AssetTransportationRequest> requestPage = null;
        boolean isFilterApplied = (requests.isFilterApplied() ? true : false);
        boolean isSortRequest   = (requests.isSortDateByTime() ? true : false);
        boolean isAssetFilterType = false;
        if (isFilterApplied) {
            if (requests.getFilterType().equals(ApplicationConstants.STATUS)) isAssetFilterType = false;
            else isAssetFilterType = true;
        }

        if (!isFilterApplied && !isSortRequest) {
            requestPage = assetTransportationRequestRepository.findAll(
                            new PageRequest(requests.getOffset(), requests.getPageSize()));
        }

        if (!isFilterApplied && isSortRequest) {
            requestPage =  assetTransportationRequestRepository.findAll(
                            new PageRequest(requests.getOffset(), requests.getPageSize(),
                            new Sort(ApplicationConstants.DATE_TIME)));
        }

        if (isFilterApplied) {
            if (isSortRequest) {
                if (isAssetFilterType) {
            requestPage = assetTransportationRequestRepository.findByAssetType(
                            requests.getAssetTypeFilter(),
                            new PageRequest(requests.getOffset(), requests.getPageSize(),
                            new Sort(ApplicationConstants.DATE_TIME)));
                } else {
            requestPage = assetTransportationRequestRepository.findByStatus(
                           requests.getStatusFilter(),
                           new PageRequest(requests.getOffset(), requests.getPageSize(),
                           new Sort(ApplicationConstants.DATE_TIME)));
                }
            } else {
                if (isAssetFilterType) {
            requestPage = assetTransportationRequestRepository.findByAssetType(
                          requests.getAssetTypeFilter(),
                          new PageRequest(requests.getOffset(), requests.getPageSize()));
                } else {
            requestPage = assetTransportationRequestRepository.findAll(
                    new PageRequest(requests.getOffset(), requests.getPageSize()));
                }
            }
        }
        return AssertTransportRequestorUtils.updateRequestorStatus(
                isFilterApplied,
                isAssetFilterType,
                requests.getStatusFilter(),
                requestPage);
    }
}