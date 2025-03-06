package com.base.orderms.service;

import com.base.orderms.listener.dto.OrderCreatedEvent;

public interface IOrderService {

    void save(OrderCreatedEvent event);
}
