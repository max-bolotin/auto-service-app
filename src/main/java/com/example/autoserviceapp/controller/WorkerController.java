package com.example.autoserviceapp.controller;

import com.example.autoserviceapp.dto.mapper.WorkerMapper;
import com.example.autoserviceapp.dto.request.WorkerRequestDto;
import com.example.autoserviceapp.dto.response.WorkerResponseDto;
import com.example.autoserviceapp.model.Order;
import com.example.autoserviceapp.model.Worker;
import com.example.autoserviceapp.service.WorkerService;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/workers")
public class WorkerController {
    private final WorkerMapper workerMapper;
    private final WorkerService workerService;

    public WorkerController(WorkerMapper workerMapper, WorkerService workerService) {
        this.workerMapper = workerMapper;
        this.workerService = workerService;
    }

    @PostMapping("/new")
    public WorkerResponseDto addWorker(@RequestBody WorkerRequestDto workerRequestDto) {
        Worker worker = workerMapper.mapToModel(workerRequestDto);
        worker = workerService.save(worker);
        return workerMapper.mapToDto(worker);
    }

    @PutMapping("/add-order")
    public WorkerResponseDto addOrder(@RequestParam(name = "worker-id") Long workerId,
                                      @RequestParam(name = "order-id") Long orderId) {
        Worker worker = workerService.addOrder(workerId, orderId);
        return workerMapper.mapToDto(worker);

    }

    @GetMapping("/orders")
    public List<Order> getOrdersByWorkerId(@RequestParam(name = "worker-id") Long workerId) {
        return workerService.getOrdersByWorkerId(workerId);
    }

    @GetMapping("/salary")
    public BigDecimal calculateSalaryByWorkerId(@RequestParam(name = "worker-id") Long workerId) {
        return workerService.calculateSalaryByWorkerId(workerId);
    }
}
