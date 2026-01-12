package com.finalProject.BDS.controller;

import com.finalProject.BDS.config.*;
import com.finalProject.BDS.model.Property;
import com.finalProject.BDS.model.User;
import com.finalProject.BDS.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/Metro")
public class LinkController {

    @Autowired
    private ImageBase64 image64;

    @Autowired
    private ListServices listServices;

    //Default
    @GetMapping("/trang-chu")
    public String viewHomePage(HttpSession session, Model model) {
        //Session
        User user = (User) session.getAttribute("user");
        if (user != null) {
            model.addAttribute("user", user);
        }
        //
        List<Property> properties = listServices.getAllProperties();
        properties.forEach(property -> {
            if (property.getImage() != null) {
                String base64Image = "data:image/*;base64," + ImageBase64.encodeImage(property.getImage());
                property.setBase64Image(base64Image);
            }
        });
        model.addAttribute("properties", properties);
        //
        List<User> agents = listServices.get8Agents();
        agents.forEach(agent -> {
            if (agent.getAvatar() != null) {
                String base64Image = "data:image/*;base64," + ImageBase64.encodeImage(agent.getAvatar());
                agent.setBase64Avatar(base64Image);
            }
            //
            if (agent.getProperty() != null) {
                agent.setPropertiesCount(agent.getProperty().size());
            }
        });
        model.addAttribute("agents", agents);

        return "index";
    }

    @GetMapping("/ve-chung-toi")
    public String viewAboutPage(Model model) {
        return "about";
    }

    @GetMapping("/bat-dong-san")
    public String viewPropertiesPage(HttpSession session, Model model) {
        //Session
        User user = (User) session.getAttribute("user");
        if (user != null) {
            model.addAttribute("user", user);
        }
        //
        List<Property> properties = listServices.getAllProperties();
        properties.forEach(property -> {
            if (property.getImage() != null) {
                String base64Image = "data:image/*;base64," + ImageBase64.encodeImage(property.getImage());
                property.setBase64Image(base64Image);
            }
        });
        model.addAttribute("properties", properties);
        //
        List<User> agents = listServices.get8Agents();
        agents.forEach(agent -> {
            if (agent.getAvatar() != null) {
                String base64Image = "data:image/*;base64," + ImageBase64.encodeImage(agent.getAvatar());
                agent.setBase64Avatar(base64Image);
            }
            //
            if (agent.getProperty() != null) {
                agent.setPropertiesCount(agent.getProperty().size());
            }
        });
        model.addAttribute("agents", agents);

        return "properties";
    }

    @GetMapping("/chi-tiet/{id}")
    public String viewDetailPage(@PathVariable("id") long id, Model model) {
        Property property = listServices.getPropertyDetail(id);
        if (property.getImage() != null) {
            String base64Image = "data:image/*;base64," + ImageBase64.encodeImage(property.getImage());
            property.setBase64Image(base64Image);
        }
        model.addAttribute("property", property);
        return "detail";
    }

    @GetMapping("/dich-vu")
    public String viewSerPage() {
        return "services";
    }

    @GetMapping("/dang-nhap")
    public String viewLoginPage() {
        return "login";
    }

    @GetMapping("/dang-ky")
    public String viewSignUpPage() {
        return "signup";
    }

    @GetMapping("/dang-ky-moi-gioi")
    public String viewAgentSignUpPage() {
        return "agentsignup";
    }

    //Extend
    @GetMapping("/mo-rong/thong-tin-ca-nhan")
    public String viewAboutPage(HttpSession session, Model model) {
        //Check Agent
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        //
        return "/extend/information";
    }
    @GetMapping("/mo-rong/BDS-da-chon")
    public String viewPropertyListPage(HttpSession session, Model model) {
        //Check Agent
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        return "/extend/propertyList";
    }
    @GetMapping("/mo-rong/tao-tin-dang")
    public String viewCreatePostPage(HttpSession session, Model model) {
        //Check Agent
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        return "/extend/createPost";
    }
    @GetMapping("/mo-rong/danh-sach-tin-da-dang")
    public String viewPostListPage(HttpSession session, Model model) {
        //Check Agent
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        return "/extend/postList";
    }

    @GetMapping("/mo-rong/danh-sach-khach-hang")
    public String viewCustomerListPage(HttpSession session, Model model) {
        //Check Agent
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        //
        return "/extend/customerList";
    }
    @GetMapping("/mo-rong/danh-sach-giao-dich")
    public String viewTransactionListPage(HttpSession session, Model model) {
        //Check Agent
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        //
        return "/extend/transactionList";
    }
    @GetMapping("/mo-rong/tao-giao-dich-mua")
    public String viewCreateSalesPage(HttpSession session, Model model) {
        //Check Agent
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        //
        return "/extend/createSales";
    }
    @GetMapping("/mo-rong/tao-giao-dich-thue")
    public String viewCreateRentalPage(HttpSession session, Model model) {
        //Check Agent
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        //
        return "/extend/createRental";
    }

}
