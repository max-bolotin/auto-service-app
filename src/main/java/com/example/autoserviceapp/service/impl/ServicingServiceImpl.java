package com.example.autoserviceapp.service.impl;

import com.example.autoserviceapp.model.Servicing;
import com.example.autoserviceapp.repository.ServicingRepository;
import com.example.autoserviceapp.repository.WorkerRepository;
import com.example.autoserviceapp.service.OrderService;
import com.example.autoserviceapp.service.ServicingService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ServicingServiceImpl implements ServicingService {
    private final ServicingRepository servicingRepository;
    private final WorkerRepository workerRepository;
    private final OrderService orderService;

    public ServicingServiceImpl(ServicingRepository servicingRepository,
                                WorkerRepository workerRepository, OrderService orderService) {
        this.servicingRepository = servicingRepository;
        this.workerRepository = workerRepository;
        this.orderService = orderService;
    }

    @Override
    public Servicing findById(Long id) {
        return servicingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No servicing found with id " + id));
    }

    @Override
    public Servicing save(Servicing servicing) {
        return servicingRepository.save(servicing);
    }

    @Override
    public List<Servicing> findAllById(List<Long> servicingIds) {
        return servicingRepository.findAllById(servicingIds);
    }

    @Override
    public Servicing addWorker(Long servicingId, Long workerId) {
        Servicing servicing = findById(servicingId);
        servicing.setWorker(workerRepository.findById(workerId).orElseThrow(
                () -> new RuntimeException("No worker found with id: " + workerId)));
        servicing.setSalaryPaid(false);
        return save(servicing);
    }

    @Override
    public Servicing addOrder(Long servicingId, Long orderId) {
        Servicing servicing = findById(servicingId);
        servicing.setOrder(orderService.findById(orderId));
        return save(servicing);
    }
}
