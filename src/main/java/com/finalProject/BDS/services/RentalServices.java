package com.finalProject.BDS.services;

import com.finalProject.BDS.model.*;
import com.finalProject.BDS.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.time.LocalDate;

@Service
public class RentalServices {

    @Autowired
    RentalRepository rentalRepository;

    public void createRentalContract(String ownerName, String ownerId,
                                     LocalDate ownerBirthday, String ownerAddress,
                                     String customerName, String customerId,
                                     LocalDate customerBirthday, String customerAddress,
                                     String propertyAddress, int propertyArea,
                                     String propertyPurpose, String propertyDetail,
                                     int period, LocalDate startDate,
                                     int paymentPerMonth, int month) {

        RentContract rentContract = new RentContract();

        rentContract.setOwnerName(ownerName);
        rentContract.setOwnerId((ownerId));
        rentContract.setOwnerBirthday(ownerBirthday);
        rentContract.setOwnerAddress((ownerAddress));

        rentContract.setCustomerName(customerName);
        rentContract.setCustomerId(customerId);
        rentContract.setCustomerBirthday(customerBirthday);
        rentContract.setCustomerAddress(customerAddress);

        rentContract.setPropertyAddress(propertyAddress);
        rentContract.setPropertyArea(propertyArea);
        rentContract.setPropertyPurpose(propertyPurpose);
        rentContract.setPropertyDescription(propertyDetail);

        rentContract.setRentalPeriod(period);
        rentContract.setStartRentDate(startDate);
        rentContract.setPaymentPerMonth(paymentPerMonth);
        rentContract.setMonth(month);

        rentalRepository.save(rentContract);
    }
}
