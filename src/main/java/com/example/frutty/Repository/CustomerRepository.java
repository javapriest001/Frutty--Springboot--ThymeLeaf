package com.example.frutty.Repository;

import com.example.frutty.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CustomerRepository extends JpaRepository<Customer , Integer> {
    @Query(value = "SELECT * FROM customers where email = ?1 ", nativeQuery = true)
    Optional<Customer> getCustomerByEmail(String email);
}
