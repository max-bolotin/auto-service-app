package com.example.autoserviceapp.service;

import com.example.autoserviceapp.model.Servicing;
import java.util.List;

public interface ServicingService {
    Servicing findById(Long id);

    Servicing save(Servicing servicing);

    List<Servicing> findAllById(List<Long> servicingIds);

    Servicing addWorker(Long servicingId, Long workerId);

    Servicing addOrder(Long servicingId, Long orderId);
}
