package com.nxtwave.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchingRequest {
    int assetTransportRequestId;
    int riderId;
    int offset;
    int pageSize;
}