package com.example.autoserviceapp.repository;

import com.example.autoserviceapp.model.Servicing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicingRepository extends JpaRepository<Servicing, Long> {
}
