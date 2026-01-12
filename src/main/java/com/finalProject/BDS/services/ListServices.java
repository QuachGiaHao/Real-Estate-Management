package com.finalProject.BDS.services;

import com.finalProject.BDS.model.*;
import com.finalProject.BDS.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.*;

@Service
public class ListServices {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    PropertyRepository propertyRepository;

    @Autowired
    UserRepository userRepository;

//Default
    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    public List<User> get8Agents() {
        return userRepository.find8Agents();
    }

//Information - html
    public void updateAvatar(String email, byte[] avatarBytes) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            user.setAvatar(avatarBytes);
            userRepository.save(user);
        }
    }

    public void updateInformation(String name, String email, String phone, String birthday, String address) {
        LocalDate birthdayDate = LocalDate.parse(birthday);
        User user = userRepository.findByEmail(email);
        if (user != null) {
            user.setName(name);
            user.setEmail(email);
            user.setPhone(phone);
            user.setBirthday(birthdayDate);
            user.setAddress(address);

            userRepository.save(user);
        }
    }

    public boolean updatePassword(String email, String oldPassword, String newPassword, String confirmPassword) {
        User user = userRepository.findByEmail(email);

        if(!newPassword.equals(confirmPassword)) {
            return false;
        }
        if(!passwordEncoder.matches(oldPassword, user.getPassword())) {
            return false;
        }

        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedPassword);
        userRepository.save(user);
        return true;
    }

    public void updateCertificate(String email, byte[] certificateBytes) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            user.setCertificateImageData(certificateBytes);
            userRepository.save(user);
        }
    }

    public void deleteAccount(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            userRepository.delete(user);
        }
    }

//Create Post - html
    public void createProperty(String saleRent, String address, String type, String name, int area,
                               Long price, String interior, int bedroom, int bathroom,
                               String description, MultipartFile image, User user) throws IOException {

        byte[] imageBytes = null;
        if (image != null && !image.isEmpty()) {
            try {
                imageBytes = image.getBytes();
            } catch (IOException e) {
                System.out.println("Error");
            }
        } else {
            try {
                Path defaultImagePath = Paths.get("src/main/resources/static/images/white.jpg");
                imageBytes = Files.readAllBytes(defaultImagePath);
            } catch (IOException e) {
                System.out.println("Error");
            }
        }

        Property property = new Property();
        property.setSale_rent(saleRent);
        property.setAddress(address);
        property.setType(type);
        property.setName(name);
        property.setArea(area);
        property.setPrice(price);
        property.setInterior(interior);
        property.setBedroom(bedroom);
        property.setBathroom(bathroom);
        property.setDescription(description);
        property.setImage(imageBytes);
        property.setUser(user);

        propertyRepository.save(property);
    }

    public Property getPropertyDetail(long id){
        return propertyRepository.findById(id).orElseThrow();
    }

    public List<Property> filter(String address, String type, String sale_rent, Long minPrice, Long maxPrice){
        return propertyRepository.filterPropertys(address, type, sale_rent, minPrice, maxPrice);
    }


}
