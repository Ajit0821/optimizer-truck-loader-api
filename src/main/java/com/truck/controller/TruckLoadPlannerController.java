package com.truck.controller;

import com.truck.dto.Request;
import com.truck.dto.Response;
import com.truck.exception.BadRequestException;
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
        if (request == null) {
            throw new BadRequestException("Request body cannot be null");
        }

        if (request.truck == null) {
            throw new BadRequestException("Truck details are required");
        }

        if (request.orders == null || request.orders.isEmpty()) {
            throw new BadRequestException("At least one order is required");
        }

        if (request.orders.size() > 22) {
            throw new BadRequestException("Maximum 22 orders allowed");
        }

        return ResponseEntity.ok(
                loadService.optimize(request.truck, request.orders)
        );
    }
}
