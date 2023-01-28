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
 * Entity class for Ride.
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "RIDE_TBL")
public class Ride {

    public enum RideStatus {
        NOT_APPLIED,
        APPLIED;
    }

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String fromLocation;
    private String toLocation;
    @JsonFormat(pattern = "yyyy-MM-dd, hh:mm aa")
    private Date dateTime;
    private int assetQuantity;
    private String travelMedium;
    private String status = RideStatus.NOT_APPLIED.name();
}
