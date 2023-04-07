package com.example.WarriorsTest.repository;

import com.example.WarriorsTest.enums.UserRoles;
import com.example.WarriorsTest.models.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RoleRepository extends JpaRepository<RoleEntity,Long> {

    Optional<RoleEntity> findByUserRole(UserRoles userRoles);


}
