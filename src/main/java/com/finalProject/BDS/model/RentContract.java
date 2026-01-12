package com.finalProject.BDS.model;

import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RentContract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //
    private String ownerName;
    private String ownerId;
    private LocalDate ownerBirthday;
    private String ownerAddress;

    //
    private String customerName;
    private String customerId;
    private LocalDate customerBirthday;
    private String customerAddress;

    //
    private String propertyAddress;
    private int propertyArea;
    private String propertyPurpose;

    @Lob
    private String propertyDescription;

    //
    private int rentalPeriod;
    private LocalDate startRentDate;
    private int paymentPerMonth;
    private int month;

}
