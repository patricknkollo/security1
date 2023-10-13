package com.security.example.secu.repositories;

import com.security.example.secu.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    @Query(value = "select * from user where email = :username", nativeQuery = true)
    public Users findUserByUsername(@Param("username") String username);

    @Query(value = "select * from user where email = :emailadresse", nativeQuery = true)
    public Users findUserByEmailAdresse(@Param("emailadresse") String email);
}
