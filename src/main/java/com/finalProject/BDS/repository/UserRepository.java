package com.finalProject.BDS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.finalProject.BDS.model.User;
import org.springframework.data.jpa.repository.Query;
import java.util.*;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByEmail(String email);

    @Query(value = "SELECT u.*, COUNT(p.id) AS property_count " + "FROM user u " +
            "LEFT JOIN property p ON u.id = p.user_id " +
            "WHERE u.is_agent = true " +
            "GROUP BY u.id " + "ORDER BY property_count DESC " + "LIMIT 8",
            nativeQuery = true)
    List<User> find8Agents();

}
