package com.nxtwave.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllRequests {
    boolean sortDateByTime;
    boolean filterApplied;
    String filterType;
    String assetTypeFilter;
    String statusFilter;
    int pageSize;
    int offset;
}
