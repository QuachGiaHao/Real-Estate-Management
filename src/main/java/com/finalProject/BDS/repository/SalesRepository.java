package com.finalProject.BDS.repository;

import com.finalProject.BDS.model.SalesContract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesRepository extends JpaRepository<SalesContract, String> {

}
