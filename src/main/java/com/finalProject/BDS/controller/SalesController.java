package com.finalProject.BDS.controller;


import com.finalProject.BDS.services.RentalServices;
import com.finalProject.BDS.services.SalesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
@RequestMapping("/Metro")
public class SalesController {

    @Autowired
    private SalesServices salesServices;

    @PostMapping("/tao-hop-dong-mua")
    public ResponseEntity<String> createRentPost(@RequestParam("ownerName") String ownerName, @RequestParam("ownerId") String ownerId,
                                                 @RequestParam("ownerBirthday") LocalDate ownerBirthday, @RequestParam("ownerAddress") String ownerAddress,
                                                 @RequestParam("customerName") String customerName, @RequestParam("customerId") String customerId,
                                                 @RequestParam("customerBirthday") LocalDate customerBirthday, @RequestParam("customerAddress") String customerAddress,
                                                 @RequestParam("propertyAddress") String propertyAddress, @RequestParam("propertyArea") int propertyArea,
                                                 @RequestParam("propertyPurpose") String propertyPurpose, @RequestParam("propertyDetail") String propertyDetail,
                                                 @RequestParam("payment") int payment, @RequestParam("startDate") LocalDate startDate,
                                                 @RequestParam("payment_methods") String payment_methods) {

        salesServices.createSalesContract(ownerName, ownerId, ownerBirthday, ownerAddress, customerName, customerId,
                customerBirthday, customerAddress, propertyAddress, propertyArea, propertyPurpose,
                propertyDetail, payment, startDate, payment_methods);

        return ResponseEntity.ok("Hợp đồng đã được tạo thành công!");
    }
}
