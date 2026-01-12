package com.finalProject.BDS.repository;

import com.finalProject.BDS.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Long> {
    @Query("SELECT p FROM Property p WHERE(:address IS NULL OR :address = '' OR p.address = :address)" +
            "AND (:type IS NULL OR :type = '' OR p.type = :type)" +
            "AND (:sale_rent IS NULL OR :sale_rent = '' OR p.sale_rent = :sale_rent)" +
            "AND (:minPrice IS NULL OR p.price >= :minPrice)" +
            "AND (:maxPrice IS NULL OR p.price < :maxPrice)")
    List<Property> filterPropertys(@Param("address") String address,
                                   @Param("type") String type,
                                   @Param("sale_rent") String sale_rent,
                                   @Param("minPrice") Long minPrice,
                                   @Param("maxPrice") Long maxPrice);
}
