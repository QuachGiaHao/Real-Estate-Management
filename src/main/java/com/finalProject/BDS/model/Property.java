package com.finalProject.BDS.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sale_rent;

    private String address;

    private String type;

    private String name;

    private int area;

    private Long price;

    private String interior;

    private int bedroom;

    private int bathroom;

    @Lob
    private String description;

    @Lob
    private byte[] image;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    //
    public Property(String sale_rent, String address, String type, String name, int area,
                    Long price, String interior, int bedroom, int bathroom, String description, byte[] image) {
        this.sale_rent = sale_rent;
        this.address = address;
        this.type = type;
        this.name = name;
        this.area = area;
        this.price = price;
        this.interior = interior;
        this.bedroom = bedroom;
        this.bathroom = bathroom;
        this.description = description;
        this.image = image;
    }

    @Transient
    private String base64Image;

}
