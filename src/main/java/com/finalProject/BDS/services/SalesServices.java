package com.finalProject.BDS.services;

import com.finalProject.BDS.model.RentContract;
import com.finalProject.BDS.model.SalesContract;
import com.finalProject.BDS.repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class SalesServices {

    @Autowired
    SalesRepository salesRepository;

    public void createSalesContract(String ownerName, String ownerId,
                                     LocalDate ownerBirthday, String ownerAddress,
                                     String customerName, String customerId,
                                     LocalDate customerBirthday, String customerAddress,
                                     String propertyAddress, int propertyArea,
                                     String propertyPurpose, String propertyDetail,
                                     int payment, LocalDate startDate,
                                     String paymentMethods) {

        SalesContract salesContract = new SalesContract();

        salesContract.setOwnerName(ownerName);
        salesContract.setOwnerId((ownerId));
        salesContract.setOwnerBirthday(ownerBirthday);
        salesContract.setOwnerAddress((ownerAddress));

        salesContract.setCustomerName(customerName);
        salesContract.setCustomerId(customerId);
        salesContract.setCustomerBirthday(customerBirthday);
        salesContract.setCustomerAddress(customerAddress);

        salesContract.setPropertyAddress(propertyAddress);
        salesContract.setPropertyArea(propertyArea);
        salesContract.setPropertyPurpose(propertyPurpose);
        salesContract.setPropertyDescription(propertyDetail);

        salesContract.setStartDate(startDate);
        salesContract.setPayment(payment);
        salesContract.setPaymentMethods(paymentMethods);

        salesRepository.save(salesContract);
    }
}
