package com.example.autoserviceapp.dto.mapper;

import com.example.autoserviceapp.dto.request.ServicingRequestDto;
import com.example.autoserviceapp.dto.response.ServicingResponseDto;
import com.example.autoserviceapp.model.Servicing;
import com.example.autoserviceapp.service.OrderService;
import com.example.autoserviceapp.service.WorkerService;
import org.springframework.stereotype.Component;

@Component
public class ServicingMapper implements RequestDtoMapper<ServicingRequestDto, Servicing>,
        ResponseDtoMapper<ServicingResponseDto, Servicing> {
    private final OrderService orderService;
    private final WorkerService workerService;

    public ServicingMapper(OrderService orderService, WorkerService workerService) {
        this.orderService = orderService;
        this.workerService = workerService;
    }

    @Override
    public Servicing mapToModel(ServicingRequestDto dto) {
        Servicing servicing = new Servicing();
        servicing.setName(dto.getName());
        //        servicing.setWorker(workerService.getById(dto.getWorkerId()));
        //        servicing.setOrder(orderService.getById(dto.getOrderId()));
        servicing.setPrice(dto.getPrice());
        servicing.setSalaryPaid(dto.isSalaryPaid());
        return servicing;
    }

    @Override
    public ServicingResponseDto mapToDto(Servicing servicing) {
        ServicingResponseDto dto = new ServicingResponseDto();
        dto.setId(servicing.getId());
        dto.setName(servicing.getName());
        dto.setPrice(servicing.getPrice());
        dto.setSalaryPaid(servicing.isSalaryPaid());
        if (servicing.getWorker() != null) {
            dto.setWorkerId(servicing.getWorker().getId());
        }
        if (servicing.getOrder() != null) {
            dto.setOrderId(servicing.getOrder().getId());
        }
        return dto;
    }
}
