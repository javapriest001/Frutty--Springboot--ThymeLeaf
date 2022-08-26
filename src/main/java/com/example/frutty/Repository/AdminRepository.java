package com.example.frutty.Repository;

import com.example.frutty.Model.Admin;
import com.example.frutty.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository< Admin, Integer> {
    @Query(value = "SELECT * FROM admins where email = ?1 ", nativeQuery = true)
    Optional<Admin> getAdminByEmail(String email);

    
}
