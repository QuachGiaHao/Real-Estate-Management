package com.finalProject.BDS.services;

import com.finalProject.BDS.model.User;
import com.finalProject.BDS.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;

@Service
public class SecurityServices {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public byte[] getDefaultAvatar() throws IOException {
        ClassPathResource resource = new ClassPathResource("static/images/user.png");
        return Files.readAllBytes(resource.getFile().toPath());
    }
    public byte[] getWhiteImage() throws IOException {
        ClassPathResource resource = new ClassPathResource("static/images/white.jpg");
        return Files.readAllBytes(resource.getFile().toPath());
    }


    public void addUser(String name, String email, String phone, LocalDate birthday,
                        String address, String password) throws IOException {
        //Password
        String encodedPassword = passwordEncoder.encode(password);
        //User
        boolean isAgent = false;
        //Avatar
        byte[] avatar = getDefaultAvatar();
        //Create
        User user = new User(name, email, phone, birthday, address, avatar, isAgent, encodedPassword);
        userRepository.save(user);
    }


    public void addAgent(String name, String email, String phone, LocalDate birthday,
                         String address, byte[] image, String password) throws IOException {
        //Password
        String encodedPassword = passwordEncoder.encode(password);
        //Agent
        boolean isAgent = true;
        //Avatar
        byte[] avatar = getDefaultAvatar();
        //
        byte[] certificateImage = image.length == 0 ? getWhiteImage() : image;
        //Create Agent
        User user = new User(name, email, phone, birthday, address, avatar, certificateImage, isAgent, encodedPassword);
        userRepository.save(user);
    }

    public User checkLogin(String email, String password) {
        User user = getUser(email);
        if(user == null) {
            return null;
        }
        boolean loginSuccessful = passwordEncoder.matches(password, user.getPassword());
        if (!loginSuccessful) {
            return null;
        }
        return user;
    }

    public User getUser(String email) {
        return userRepository.findByEmail(email);
    }
}
