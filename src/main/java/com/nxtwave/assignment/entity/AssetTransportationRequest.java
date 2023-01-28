package com.nxtwave.assignment.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Entity class for asset transportation request.
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ASSETTRANSPORTATIONREQUEST_TBL")
public class AssetTransportationRequest {

    public enum AssetTransportRequestStatus {
        PENDING,
        EXPIRED;
    }

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String fromLocation;
    private String toLocation;
    @JsonFormat(pattern = "yyyy-MM-dd, hh:mm aa")
    private Date dateTime;
    private int noOfAssets;
    private String assetType;
    private String assetSensitivity;
    private String whomToDeliver;
    private String status = AssetTransportRequestStatus.PENDING.name();
}
