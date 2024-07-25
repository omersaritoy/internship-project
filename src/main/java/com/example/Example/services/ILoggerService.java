package com.example.Example.services;

import com.example.Example.entities.Logger;

import java.util.Date;
import java.util.List;

public interface ILoggerService {
    void log(String action, String details, String entityType,Long entityId);
    List<Logger> getAllLogs();
    Logger getLogByIdAndEntityType(Long entityId,String entityType);
}
