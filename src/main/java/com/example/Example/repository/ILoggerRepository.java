package com.example.Example.repository;

import com.example.Example.entities.Logger;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ILoggerRepository extends JpaRepository<Logger, Long> {

    Logger findByEntityIdAndEntityType(Long entityId, String entityType);

    @Query("select l from Logger l")
    List<Logger> getLogs();

    @Query("SELECT l FROM Logger l WHERE l.id > :id")
    List<Logger> findByIdGreaterThan(Long id);

    @Query("select l from Logger l where l.entityType=:entityType")
    List<Logger> findByEntityType(@Param("entityType") String entityType);

    @Query("select l from Logger l where l.action=:action")
    List<Logger> findByAction(@Param("action") String action);


}
