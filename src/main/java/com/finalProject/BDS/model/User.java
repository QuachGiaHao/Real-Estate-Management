package com.finalProject.BDS.model;

import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String phone;

    private LocalDate birthday;

    private String address;

    @Lob
    private byte[] avatar;

    @Lob
    private byte[] certificateImageData;

    public boolean isAgent;

    public String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Property> property;

    //
    public User(String name, String email, String phone, LocalDate birthday,
                String address, byte[] avatar, boolean isAgent, String password) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.birthday = birthday;
        this.address = address;
        this.avatar = avatar;
        this.password = password;
        this.isAgent = isAgent;
    }

    public User(String name, String email, String phone, LocalDate birthday,
                String address, byte[] avatar, byte[] certificateImageData, boolean isAgent, String password) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.birthday = birthday;
        this.address = address;
        this.avatar = avatar;
        this.certificateImageData = certificateImageData;
        this.password = password;
        this.isAgent = isAgent;
    }

    @Transient
    private String base64Avatar;

    @Transient
    private int propertiesCount;

}
