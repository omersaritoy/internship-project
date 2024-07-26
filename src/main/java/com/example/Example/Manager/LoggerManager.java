package com.example.Example.Manager;

import com.example.Example.entities.Logger;
import com.example.Example.repository.ILoggerRepository;
import com.example.Example.services.ILoggerService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;

import java.util.List;
import java.util.Map;

@Service
public class LoggerManager implements ILoggerService {
    private final ILoggerRepository loggerRepository;
    @PersistenceContext//JPA (Java Persistence API) kullanırken EntityManager nesnelerini enjekte etmek için kullanılan
    // bir anotasyondur.
    private EntityManager entityManager;

    @Autowired
    public LoggerManager(ILoggerRepository loggerRepository, EntityManager entityManager) {
        this.loggerRepository = loggerRepository;

    }

    @Override
    public void log(String action, String details, String entityType, Long entityId) {

        Logger logEntry = new Logger(action, details, entityType, LocalDateTime.now(), entityId);
        loggerRepository.save(logEntry);
    }

    @Override
    public List<Logger> getLoggers() {
        return loggerRepository.getLogs();
    }

    @Override
    public List<Logger> getAllLogs() {
        return loggerRepository.findAll();
    }

    @Override
    public Logger getLogByIdAndEntityType(Long entityId, String entityType) {
        return loggerRepository.findByEntityIdAndEntityType(entityId, entityType);
    }

    @Override
    public List<Logger> findByIdGreaterThan(Long id) {
        return loggerRepository.findByIdGreaterThan(id);
    }

    @Override
    public List<Logger> findByEntityType(String entityType) {
        return loggerRepository.findByEntityType(entityType);
    }

    @Override
    public List<Logger> findByAction(String action) {
        return loggerRepository.findByAction(action);
    }

    @Override
    public List<Logger> getLogsByEntityTypeAndAction(Map<String, Object> params) {


        // Dinamik SQL sorgusu oluşturma
        StringBuilder sql = new StringBuilder("SELECT l FROM Logger l WHERE 1=1");

        // Eğer entityType parametresi varsa, sorguya ekle
        if (params.get("entityType") != null && !params.get("entityType").equals("")) {
            sql.append(" AND l.entityType = :entityType");
        }
        // Eğer action parametresi varsa, sorguya ekle
        if (params.get("action") != null && !params.get("action").equals("")) {
            sql.append(" AND l.action = :action");
        }

        // Sorguyu oluştur ve parametreleri ayarla
        Query query = entityManager.createQuery(sql.toString(), Logger.class);

        if (params.get("entityType") != null && !params.get("entityType").equals("")) {
            query.setParameter("entityType", params.get("entityType"));
        }
        if (params.get("action") != null && !params.get("action").equals("")) {
            query.setParameter("action", params.get("action"));
        }

        // Sonuçları döndür
        return query.getResultList();

    }
}
