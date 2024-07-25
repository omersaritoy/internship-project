package com.example.Example.repository;

import com.example.Example.entities.Logger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ILoggerRepository extends JpaRepository<Logger,Long> {
    Optional<Logger> findByEntityIdAndEntityType(Long entityId, String entityType);
}
