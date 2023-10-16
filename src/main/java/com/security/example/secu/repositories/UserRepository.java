package com.security.example.secu.repositories;

import com.security.example.secu.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    @Query(value = "select userid, username, password, email " +
            "from users " +
            "where username =:name", nativeQuery = true)
    public Optional<Users> findUserByUsername(@Param("name") String username);

    @Query(value = "select userid, username, password, email " +
            "from users " +
            "where email =:emailadresse", nativeQuery = true)
    public Optional<Users> findUserByEmailAdresse(@Param("emailadresse") String email);
}