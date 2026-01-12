package com.finalProject.BDS.controller;

import com.finalProject.BDS.services.RentalServices;
import com.finalProject.BDS.services.SecurityServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import jakarta.servlet.http.HttpSession;
import com.finalProject.BDS.model.User;

@Controller
@RequestMapping("/Metro")
public class RentalController {

    @Autowired
    private RentalServices rentalServices;

    @PostMapping("/tao-hop-dong-thue")
    public ResponseEntity<String> createRentPost(@RequestParam("ownerName") String ownerName, @RequestParam("ownerId") String ownerId,
                                 @RequestParam("ownerBirthday") LocalDate ownerBirthday, @RequestParam("ownerAddress") String ownerAddress,
                                 @RequestParam("customerName") String customerName, @RequestParam("customerId") String customerId,
                                 @RequestParam("customerBirthday") LocalDate customerBirthday, @RequestParam("customerAddress") String customerAddress,
                                 @RequestParam("propertyAddress") String propertyAddress, @RequestParam("propertyArea") int propertyArea,
                                 @RequestParam("propertyPurpose") String propertyPurpose, @RequestParam("propertyDetail") String propertyDetail,
                                 @RequestParam("period") int period, @RequestParam("startDate") LocalDate startDate,
                                 @RequestParam("paymentPerMonth") int paymentPerMonth, @RequestParam("month") int month) {

        rentalServices.createRentalContract(ownerName, ownerId, ownerBirthday, ownerAddress, customerName, customerId,
                                            customerBirthday, customerAddress, propertyAddress, propertyArea, propertyPurpose,
                                            propertyDetail, period, startDate, paymentPerMonth, month);

        return ResponseEntity.ok("Hợp đồng đã được tạo thành công!");
    }

}
