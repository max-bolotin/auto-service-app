package com.example.autoserviceapp.service.impl;

import com.example.autoserviceapp.model.Order;
import com.example.autoserviceapp.model.Worker;
import com.example.autoserviceapp.model.WorkerSalary;
import com.example.autoserviceapp.repository.ServicingRepository;
import com.example.autoserviceapp.repository.WorkerRepository;
import com.example.autoserviceapp.service.OrderService;
import com.example.autoserviceapp.service.WorkerService;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class WorkerServiceImpl implements WorkerService {
    private final WorkerRepository workerRepository;
    private final ServicingRepository servicingRepository;
    private final OrderService orderService;

    public WorkerServiceImpl(WorkerRepository workerRepository,
                             ServicingRepository servicingRepository,
                             OrderService orderService) {
        this.workerRepository = workerRepository;
        this.servicingRepository = servicingRepository;
        this.orderService = orderService;
    }

    @Override
    public Worker findById(Long id) {
        return workerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No worker found with id " + id));
    }

    @Override
    public Worker save(Worker worker) {
        return workerRepository.save(worker);
    }

    @Override
    public Worker addOrder(Long workerId, Long orderId) {
        Worker worker = findById(workerId);
        List<Order> orders = worker.getFulfilledOrders();
        orders.add(orderService.findById(orderId));
        worker.setFulfilledOrders(orders);
        return save(worker);
    }

    @Override
    public List<Order> getOrdersByWorkerId(Long workerId) {
        Worker worker = findById(workerId);
        return worker.getFulfilledOrders();
    }

    @Override
    public BigDecimal calculateSalaryByWorkerId(Long workerId) {
        Worker worker = findById(workerId);
        WorkerSalary salary = new WorkerSalary(worker, servicingRepository);
        return salary.getSalary();
    }
}
