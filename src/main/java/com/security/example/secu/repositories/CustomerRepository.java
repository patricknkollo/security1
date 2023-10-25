package com.security.example.secu.repositories;

import com.security.example.secu.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(value = "select customerid,lastname,firstname, username, password, email " +
            "from Customer " +
            "where username =:name", nativeQuery = true)
    public Optional<Customer> findCustomerByUsername(@Param("name") String username);

    @Query(value = "select customerid,lastname,firstname, username, password, email  " +
            "from Customer " +
            "where email =:emailadresse", nativeQuery = true)
    public Optional<Customer> findCustomerByEmailAdresse(@Param("emailadresse") String email);
}

/**
 * private Long customerid;
 *     private String lastname;
 *     private String firstname;
 *     private String username;
 *     private String password;
 *     private String email;
 */
