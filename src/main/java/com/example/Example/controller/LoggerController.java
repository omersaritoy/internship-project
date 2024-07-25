package com.example.Example.controller;

import com.example.Example.entities.Logger;
import com.example.Example.services.ILoggerService;
import com.example.Example.utilities.DataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/logs")
public class LoggerController {

    private final ILoggerService loggerService;

    @Autowired
    public LoggerController(ILoggerService loggerService) {
        this.loggerService = loggerService;
    }

    @GetMapping("/getAll")
    public DataResult<List<Logger>> getAllLogs() {
        List<Logger> logs = loggerService.getAllLogs();
        return new DataResult<>(true, "Tüm kayıtlar getirildi",logs);
    }

    @GetMapping("/getByIdAndEntityIdAndEntityType/{entitiyId}/{entityType}")
    public DataResult<Logger> getLogByIdAndEntityType(@PathVariable Long entitiyId, @PathVariable String entityType) {
        // bunu farklı kullanıcı ekledi
        // bunu maine ekledik
        Logger log = loggerService.getLogByIdAndEntityType(entitiyId, entityType);
        return new DataResult<>(true, "Kayıt getirildi",log);
    }
}
