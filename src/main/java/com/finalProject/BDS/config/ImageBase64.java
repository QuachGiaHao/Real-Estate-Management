package com.finalProject.BDS.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.*;

@Configuration
public class ImageBase64 {

    @Bean
    public static String encodeImage(byte[] imageData) {
        return Base64.getEncoder().encodeToString(imageData);
    }
}
