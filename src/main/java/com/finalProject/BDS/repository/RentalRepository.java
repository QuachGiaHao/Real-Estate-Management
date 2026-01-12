package com.finalProject.BDS.repository;

import com.finalProject.BDS.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<RentContract, Long>  {
}
