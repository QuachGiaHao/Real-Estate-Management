package com.finalProject.BDS.model;

import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SalesContract {

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
    private LocalDate startDate;
    private int payment;
    private String paymentMethods;

}
