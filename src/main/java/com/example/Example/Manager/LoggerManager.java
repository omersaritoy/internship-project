package com.example.Example.Manager;

import com.example.Example.entities.Logger;
import com.example.Example.repository.ILoggerRepository;
import com.example.Example.services.ILoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
public class LoggerManager implements ILoggerService {
    private final ILoggerRepository loggerRepository;

    @Autowired
    public LoggerManager(ILoggerRepository loggerRepository) {
        this.loggerRepository = loggerRepository;
    }

    @Override
    public void log(String action, String details, String entityType, Long entityId) {

        Logger logEntry = new Logger(action, details, entityType,LocalDateTime.now(), entityId);
        loggerRepository.save(logEntry);
    }

    @Override
    public List<Logger> getAllLogs() {
        return loggerRepository.findAll();
    }

    @Override
    public Logger getLogByIdAndEntityType(Long entityId, String entityType) {
        return loggerRepository.findByEntityIdAndEntityType(entityId, entityType).orElse(null);
    }
}
