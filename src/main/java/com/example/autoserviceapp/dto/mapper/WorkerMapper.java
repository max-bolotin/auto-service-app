package com.example.autoserviceapp.dto.mapper;

import com.example.autoserviceapp.dto.request.WorkerRequestDto;
import com.example.autoserviceapp.dto.response.WorkerResponseDto;
import com.example.autoserviceapp.model.Order;
import com.example.autoserviceapp.model.Worker;
import com.example.autoserviceapp.service.OrderService;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class WorkerMapper implements RequestDtoMapper<WorkerRequestDto, Worker>,
        ResponseDtoMapper<WorkerResponseDto, Worker> {
    private final OrderService orderService;

    public WorkerMapper(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public Worker mapToModel(WorkerRequestDto dto) {
        Worker worker = new Worker();
        worker.setName(dto.getName());
        /*        worker.setFulfilledOrders(dto.getFulfilledOrderIds()
                .stream()
                .map(orderService::findById)
                .collect(Collectors.toList()));*/
        return worker;
    }

    @Override
    public WorkerResponseDto mapToDto(Worker worker) {
        WorkerResponseDto dto = new WorkerResponseDto();
        dto.setId(worker.getId());
        dto.setName(worker.getName());
        if (worker.getFulfilledOrders() != null) {
            dto.setFulfilledOrderIds(worker.getFulfilledOrders().stream()
                    .map(Order::getId)
                    .collect(Collectors.toList()));
        }
        return dto;
    }
}
