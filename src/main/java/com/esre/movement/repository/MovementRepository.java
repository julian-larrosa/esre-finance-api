package com.esre.movement.repository;

import java.util.Optional;
import java.util.UUID;

import org.hibernate.query.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import com.esre.movement.entity.Movement;

@Repository
public interface MovementRepository extends JpaRepository<Movement, UUID> {
    
     
    @Query("SELECT m FROM Movement m " +
           "LEFT JOIN FETCH m.category " +
           "WHERE m.user.id = :userId")
    Page findAllByUserId(@Param("userId") UUID userId, Pageable pageable);
    
    Optional<Movement> findByIdAndUserId(UUID id, UUID userId);
}

