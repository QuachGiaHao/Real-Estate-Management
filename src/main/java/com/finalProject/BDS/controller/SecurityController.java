package com.finalProject.BDS.controller;

import com.finalProject.BDS.services.SecurityServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.time.LocalDate;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import jakarta.servlet.http.HttpSession;
import com.finalProject.BDS.model.User;

@Controller
@RequestMapping("/Metro")
public class SecurityController {

    @Autowired
    private SecurityServices securityServices;

    @PostMapping("/dang-ky")
    public String addUser(@RequestParam String name, @RequestParam String email,
                          @RequestParam String phone, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate birthday,
                          @RequestParam String address, @RequestParam String password,
                          @RequestParam String confirmPassword, Model model) throws IOException {
        //Check password
        if(!password.equals(confirmPassword)) {
            model.addAttribute("error", "Mật khẩu không khớp.");
            return "signup";
        }
        //Success
        model.addAttribute("message", "Bạn vừa tạo tài khoản thành công. Vui lòng trở lại trang đăng nhập.");
        securityServices.addUser(name, email, phone, birthday, address, password);
        return "signup";
    }

    @PostMapping("/dang-ky-moi-gioi")
    public String addAgent(@RequestParam String name, @RequestParam String email,
                          @RequestParam String phone, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate birthday,
                          @RequestParam String address, @RequestParam MultipartFile image,
                          @RequestParam String password, @RequestParam String confirmPassword, Model model) throws IOException {
        //Check password
        if(!password.equals(confirmPassword)) {
            model.addAttribute("error", "Mật khẩu không khớp.");
            return "agentsignup";
        }
        //Success
        byte[] imageBytes = new byte[0];
        if (image != null && !image.isEmpty()) {
            try {
                imageBytes = image.getBytes();
            } catch (IOException e) {
                model.addAttribute("error", "Lỗi khi tải hình ảnh lên.");
                return "agentsignup";
            }
        }
        securityServices.addAgent(name, email, phone, birthday, address, imageBytes, password);
        model.addAttribute("message", "Bạn vừa tạo tài khoản thành công. Vui lòng trở lại trang đăng nhập.");
        return "agentsignup";
    }

    @PostMapping("/dang-nhap")
    public String login(@RequestParam String email, @RequestParam String password, HttpSession session, Model model) {
        User user = securityServices.checkLogin(email, password);
        if (user != null) {
            session.setAttribute("user", user);
            return "redirect:/Metro/trang-chu";
        } else {
            model.addAttribute("error", "Sai tên người dùng hoặc mật khẩu.");
            return "login";
        }
    }

    @GetMapping("/dang-xuat")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/Metro/dang-nhap";
    }

}
