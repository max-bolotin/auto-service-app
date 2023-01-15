package com.example.autoserviceapp.controller;

import com.example.autoserviceapp.dto.mapper.ServicingMapper;
import com.example.autoserviceapp.dto.request.ServicingRequestDto;
import com.example.autoserviceapp.dto.response.ServicingResponseDto;
import com.example.autoserviceapp.model.Servicing;
import com.example.autoserviceapp.service.ServicingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/servicings")
public class ServicingController {
    private final ServicingMapper servicingMapper;
    private final ServicingService servicingService;

    public ServicingController(ServicingMapper servicingMapper, ServicingService servicingService) {
        this.servicingMapper = servicingMapper;
        this.servicingService = servicingService;
    }

    @PostMapping("/new")
    public ServicingResponseDto addServicing(@RequestBody ServicingRequestDto servicingRequestDto) {
        Servicing servicing = servicingMapper.mapToModel(servicingRequestDto);
        servicing = servicingService.save(servicing);
        return servicingMapper.mapToDto(servicing);
    }

    @PutMapping("/add-order")
    public ServicingResponseDto addOrder(@RequestParam(name = "servicing-id") Long servicingId,
                                         @RequestParam(name = "order-id") Long orderId) {
        Servicing servicing = servicingService.addOrder(servicingId, orderId);
        return servicingMapper.mapToDto(servicing);
    }

    @PutMapping("/add-worker")
    public ServicingResponseDto addWorker(@RequestParam(name = "servicing-id") Long servicingId,
                                          @RequestParam(name = "worker-id") Long workerId) {
        Servicing servicing = servicingService.addWorker(servicingId, workerId);
        return servicingMapper.mapToDto(servicing);
    }
}
