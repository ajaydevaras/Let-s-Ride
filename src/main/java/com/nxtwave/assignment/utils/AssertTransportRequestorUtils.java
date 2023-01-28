package com.nxtwave.assignment.utils;

import com.nxtwave.assignment.entity.AssetTransportationRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility methods for asset transport requests.
 */
public class AssertTransportRequestorUtils {
    public static Page<AssetTransportationRequest> updateRequestorStatus(
            boolean isFilterApplied,
            boolean isAssetFilterType,
            String status,
            Page<AssetTransportationRequest> input) {
        List<AssetTransportationRequest> updatedResults = new ArrayList<>();
        for (AssetTransportationRequest request : input.getContent()) {
            if (request.getDateTime().getTime() < System.currentTimeMillis()) {
                request.setStatus(AssetTransportationRequest.AssetTransportRequestStatus.EXPIRED.name());
            }
            if (isFilterApplied && !isAssetFilterType) {
                if (request.getStatus().equals(status)) updatedResults.add(request);
            } else {
                updatedResults.add(request);
            }
        }
        return new PageImpl<>(updatedResults);
    }
}
