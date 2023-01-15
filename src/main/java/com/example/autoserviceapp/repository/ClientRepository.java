package com.example.autoserviceapp.repository;

import com.example.autoserviceapp.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
