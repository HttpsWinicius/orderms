package com.base.orderms.service;

import com.base.orderms.entity.OrderEntity;
import com.base.orderms.listener.dto.OrderCreatedEvent;
import com.base.orderms.repository.OrderRepository;
import com.base.orderms.service.mapper.OrderMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OrderService implements IOrderService {

    private final OrderRepository orderRepository;

    public OrderService (OrderRepository orderRepository ) {
        this.orderRepository = orderRepository;
    }

    public void save(OrderCreatedEvent event) {

        OrderMapper mapper = new OrderMapper();
        OrderEntity entity;

        entity = mapper.mapperOrderEntity(event, calcuteTotal(event));

        orderRepository.save(entity);
    }

    private BigDecimal calcuteTotal(OrderCreatedEvent event) {
        return event.itens()
                .stream()
                .map(item -> item.preco().multiply(BigDecimal.valueOf(item.quantidade())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);

    }
}
