package com.example.autoserviceapp.service.impl;

import com.example.autoserviceapp.model.Order;
import com.example.autoserviceapp.model.OrderStatus;
import com.example.autoserviceapp.model.Servicing;
import com.example.autoserviceapp.model.Worker;
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
        List<Order> orders = worker.getFulfilledOrders();
        BigDecimal salary = BigDecimal.ZERO;
        for (Order order : orders) {
            if (order.getOrderStatus().equals(OrderStatus.FULFILLEDSUCCESSFULLY)) {
                List<Servicing> servicings = order.getServicings();
                if (servicings.size() > 1) {
                    for (int i = 1; i < servicings.size(); i++) {
                        if (servicings.get(i).getWorker().getId() == workerId
                                    && !servicings.get(i).isSalaryPaid()) {
                            salary = salary.add(servicings.get(i).getPrice()
                                    .multiply(BigDecimal.valueOf(0.4)));
                            servicings.get(i).setSalaryPaid(true);
                            servicingRepository.save(servicings.get(i));
                        }
                    }
                } else {
                    salary = servicings.get(0).getPrice().multiply(BigDecimal.valueOf(0.4));
                    servicings.get(0).setSalaryPaid(true);
                    servicingRepository.save(servicings.get(0));
                }
            }
        }
        return salary;
    }
}
