package com.example.WarriorsTest.repository;

import com.example.WarriorsTest.models.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findUserByUsername(String userName);

    Optional<UserEntity> findByEmail(String email);


}
