package com.truck.controller;

import com.truck.dto.Request;
import com.truck.dto.Response;
import com.truck.service.LoadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class TruckLoadPlannerController {
    private final LoadService loadService;

    public TruckLoadPlannerController(LoadService loadService) {
        this.loadService = loadService;
    }

    @PostMapping("/load-optimizer")
    public ResponseEntity<Response> optimize(@RequestBody Request request) {
        if (request.truck == null || request.orders == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(
                loadService.optimize(request.truck, request.orders)
        );
    }
}
