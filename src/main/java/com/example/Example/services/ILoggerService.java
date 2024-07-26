package com.example.Example.services;

import com.example.Example.entities.Logger;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ILoggerService {
    void log(String action, String details, String entityType, Long entityId);

    List<Logger> getAllLogs();

    List<Logger> getLoggers();

    Logger getLogByIdAndEntityType(Long entityId, String entityType);

    List<Logger> findByIdGreaterThan(Long id);

    List<Logger> getLogsByEntityTypeAndAction(Map<String, Object> params);

    List<Logger> findByEntityType(String entityType);

    List<Logger> findByAction(String action);
}
