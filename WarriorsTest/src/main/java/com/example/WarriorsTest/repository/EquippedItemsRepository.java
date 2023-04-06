package com.example.WarriorsTest.repository;

import com.example.WarriorsTest.models.entity.EquippedItemsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquippedItemsRepository extends JpaRepository<EquippedItemsEntity,Long> {


}
