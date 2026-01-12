package com.finalProject.BDS.controller;

import com.finalProject.BDS.config.ImageBase64;
import com.finalProject.BDS.model.*;
import com.finalProject.BDS.repository.*;
import com.finalProject.BDS.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/Metro")
public class ListController {

    @Autowired
    private ImageBase64 imageBase64;

    @Autowired
    private SecurityServices securityServices;

    @Autowired
    private ListServices listServices;

    @Autowired
    private UserRepository userRepository;

//Default
    //Home-Search
    @GetMapping("/tim-kiem-bat-dong-san")
    public String searchProperties(@RequestParam(required = false) String address,
                                   @RequestParam(required = false) String type,
                                   @RequestParam(required = false) String saleRent,
                                   @RequestParam(required = false) Long minPrice,
                                   @RequestParam(required = false) Long maxPrice,
                                   Model model) {
        List<Property> properties = listServices.filter(address, type, saleRent, minPrice, maxPrice);
        for (Property property : properties) {
            if (property.getImage() != null) {
                String base64Image = "data:image/*;base64," + ImageBase64.encodeImage(property.getImage());
                property.setBase64Image(base64Image);
            }
        }
        model.addAttribute("properties", properties);
        return "fragments/propertyList :: propertyList";
    }

//Information - html
    //Get Image
    @GetMapping("/avatar/{userEmail}")
    @ResponseBody
    public byte[] getAvatar(@PathVariable String userEmail) {
        User user = securityServices.getUser(userEmail);
        if (user != null && user.getAvatar() != null) {
            return user.getAvatar();
        }
        return new byte[0];
    }
    @GetMapping("/certificate/{userEmail}")
    @ResponseBody
    public byte[] getCertificate(@PathVariable String userEmail) {
        User user = securityServices.getUser(userEmail);
        if (user != null && user.getCertificateImageData() != null) {
            return user.getCertificateImageData();
        }
        return new byte[0];
    }

    //Avatar
    @PutMapping("/cap-nhat-anh-dai-dien")
    public ResponseEntity<String> updateAvatar(@RequestParam("avatar") MultipartFile avatar,
                                               @RequestParam("email") String email) {
        try {
            if (avatar.isEmpty()) {
                return ResponseEntity.badRequest().body("Tệp ảnh không hợp lệ.");
            }
            if (!Objects.requireNonNull(avatar.getContentType()).startsWith("image/")) {
                return ResponseEntity.badRequest().body("Chỉ chấp nhận tệp ảnh.");
            }
            byte[] avatarBytes = avatar.getBytes();
            listServices.updateAvatar(email, avatarBytes);
            return ResponseEntity.ok("");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("");
        }
    }

    //Information
    @PutMapping("/cap-nhat-thong-tin")
    public ResponseEntity<String> updateInformation(@RequestParam("name") String name, @RequestParam("email") String email,
                                                    @RequestParam("phone") String phone, @RequestParam("birthday") String birthday,
                                                    @RequestParam("address") String address, HttpSession session) {
        listServices.updateInformation(name, email, phone, birthday, address);
        User user = userRepository.findByEmail(email);
        if (user != null) {
            session.setAttribute("user", user);
        }
        return ResponseEntity.ok("");
    }

    //Password
    @PutMapping("/cap-nhat-mat-khau")
    public ResponseEntity<String> updatePassword(@RequestParam("oldPassword") String oldPassword,
                                                 @RequestParam("newPassword") String newPassword,
                                                 @RequestParam("confirmPassword") String confirmPassword,
                                                 @RequestParam("email") String email) {
        boolean setPassword =  listServices.updatePassword(email, oldPassword, newPassword, confirmPassword);
        if(setPassword) {
            return ResponseEntity.ok("");
        } else {
            return ResponseEntity.status(401).body("Mật khẩu cũ không đúng hoặc sai Mật khẩu mới.");
        }
    }

    //Certificate
    @PutMapping("/cap-nhat-anh-chung-chi")
    public ResponseEntity<String> updateCertificate(@RequestParam("certificate") MultipartFile certificate,
                                                    @RequestParam("email") String email) {
        try {
            if (certificate.isEmpty()) {
                return ResponseEntity.badRequest().body("Tệp ảnh không hợp lệ.");
            }
            if (!Objects.requireNonNull(certificate.getContentType()).startsWith("image/")) {
                return ResponseEntity.badRequest().body("Chỉ chấp nhận tệp ảnh.");
            }
            byte[] certificateBytesBytes = certificate.getBytes();
            listServices.updateCertificate(email, certificateBytesBytes);
            return ResponseEntity.ok("");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("");
        }
    }

    //Delete Account
    @DeleteMapping("/xoa-tai-khoan")
    public ResponseEntity<String> deleteAccount(@RequestParam("email") String email, HttpSession session) {
        listServices.deleteAccount(email);
        session.invalidate();
        return ResponseEntity.ok("");
    }

//Create Post - html
    @PostMapping("/tao-bat-dong-san-moi")
    public String createProperty(@RequestParam("sale_rent") String saleRent, @RequestParam("address") String address,
                                 @RequestParam("type") String type, @RequestParam("name") String name,
                                 @RequestParam("area") int area, @RequestParam("price") Long price,
                                 @RequestParam("interior") String interior, @RequestParam("bedroom") int bedroom,
                                 @RequestParam("bathroom") int bathroom, @RequestParam("description") String description,
                                 @RequestParam(value = "image", required = false) MultipartFile image, HttpSession session) throws IOException {
        User user = (User) session.getAttribute("user");
        listServices.createProperty(saleRent, address, type, name, area, price, interior, bedroom, bathroom, description, image, user);
        return "redirect:/Metro/mo-rong/tao-tin-dang";
    }



}
