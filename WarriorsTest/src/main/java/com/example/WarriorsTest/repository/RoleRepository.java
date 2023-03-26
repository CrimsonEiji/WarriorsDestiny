package com.example.WarriorsTest.repository;

import com.example.WarriorsTest.models.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<RoleEntity,Long> {
}
