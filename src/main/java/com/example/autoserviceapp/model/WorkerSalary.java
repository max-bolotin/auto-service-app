package com.example.autoserviceapp.model;

import com.example.autoserviceapp.repository.ServicingRepository;
import java.math.BigDecimal;
import java.util.List;
import lombok.Data;

@Data
public class WorkerSalary {
    private static final BigDecimal SALARY_PERCENTAGE = BigDecimal.valueOf(0.4);
    private static final int DIAGNOSTICS_POSITION = 0;
    private Worker worker;
    private final ServicingRepository servicingRepository;

    public WorkerSalary(Worker worker, ServicingRepository servicingRepository) {
        this.worker = worker;
        this.servicingRepository = servicingRepository;
    }

    public BigDecimal getSalary() {
        List<Order> orders = worker.getFulfilledOrders();
        BigDecimal salary = BigDecimal.ZERO;
        for (Order order : orders) {
            if (order.getOrderStatus().equals(OrderStatus.FULFILLEDSUCCESSFULLY)) {
                List<Servicing> servicings = order.getServicings();
                if (servicings.size() > 1) {
                    for (int i = 1; i < servicings.size(); i++) {
                        if (servicings.get(i).getWorker().getId() == worker.getId()
                                && !servicings.get(i).isSalaryPaid()) {
                            salary = salary.add(servicings.get(i).getPrice()
                                    .multiply(SALARY_PERCENTAGE));
                            servicings.get(i).setSalaryPaid(true);
                            servicingRepository.save(servicings.get(i));
                        }
                    }
                } else {
                    salary = servicings.get(DIAGNOSTICS_POSITION).getPrice()
                            .multiply(SALARY_PERCENTAGE);
                    servicings.get(DIAGNOSTICS_POSITION).setSalaryPaid(true);
                    servicingRepository.save(servicings.get(DIAGNOSTICS_POSITION));
                }
            }
        }
        return salary;
    }
}
