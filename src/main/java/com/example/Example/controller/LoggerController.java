package com.example.Example.controller;

import com.example.Example.entities.Logger;
import com.example.Example.services.ILoggerService;
import com.example.Example.utilities.DataResult;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/logs")
public class LoggerController {

    private final ILoggerService loggerService;

    @Autowired
    public LoggerController(ILoggerService loggerService) {
        this.loggerService = loggerService;
    }

    @GetMapping("/getLogs")
    public DataResult<List<Logger>> getLogs() {
        return new DataResult<>(true, "Tüm kayıtlar getirildi", loggerService.getLoggers());
    }

    @GetMapping("/greater-than/{id}")
    public DataResult<List<Logger>> getLogsByIdGreaterThan(@PathVariable long id) {
        return new DataResult<>(true, "id durumuna göre kayyıtlar getirildi", loggerService.findByIdGreaterThan(id));
    }

    @GetMapping("/getAll")
    public DataResult<List<Logger>> getAllLogs() {

        return new DataResult<>(true, "Tüm kayıtlar getirildi", loggerService.getAllLogs());
    }

    @GetMapping("/getByEntityType/{entityType}")
    public DataResult<List<Logger>> getLogsByEntityType(@PathVariable String entityType) {
        return new DataResult<>(true, "EntityType'a göre veriler getirildi", loggerService.findByEntityType(entityType));
    }

    @GetMapping("/getByAction/{action}")
    public DataResult<List<Logger>> findByAction(@PathVariable String action) {
        return new DataResult<>(true, "action'a göre veriler getirildi", loggerService.findByAction(action));
    }

    @GetMapping("/getByParams")
    public DataResult<List<Logger>> findByEntityTypeAndAction(@RequestBody Map<String, Object> entityTypeAndAction) {
        return new DataResult<>(true, "EntityType ve action'a göre veriler getirildi", loggerService.getLogsByEntityTypeAndAction(entityTypeAndAction));
    }

    @GetMapping("/getByIdAndEntityIdAndEntityType/{entitiyId}/{entityType}")
    public DataResult<Logger> getLogByIdAndEntityType(@PathVariable Long entitiyId, @PathVariable String entityType) {
        Logger log = loggerService.getLogByIdAndEntityType(entitiyId, entityType);
        return new DataResult<>(true, "Kayıt getirildi", log);
    }
}
