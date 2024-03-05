package com.example.backend.authentication.repo;

import com.example.backend.authentication.entity.NewUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface UserRepository extends JpaRepository<NewUser, Integer> {
    Optional<NewUser> findOneByEmailAndPassword(String email, String Password);
    NewUser findByEmail(String email);

    boolean existsByEmail(String email);

}
