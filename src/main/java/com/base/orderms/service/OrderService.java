package com.base.orderms.service;

import com.base.orderms.entity.OrderEntity;
import com.base.orderms.listener.dto.OrderCreatedEvent;
import com.base.orderms.repository.OrderRepository;
import com.base.orderms.service.mapper.OrderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OrderService implements IOrderService {

    private final Logger logger = LoggerFactory.getLogger(OrderService.class);

    private final OrderRepository orderRepository;

    public OrderService (OrderRepository orderRepository ) {
        this.orderRepository = orderRepository;
    }

    public void save(OrderCreatedEvent event) {

        logger.info("Process order {}", event.codigoPedido());

        OrderMapper mapper = new OrderMapper();
        OrderEntity entity;

        entity = mapper.mapperOrderEntity(event, calcuteTotal(event));

        logger.info("Save order {} on the table", event.codigoPedido());

        orderRepository.save(entity);

        logger.info("Finish process for order {}", event.codigoPedido());
    }

    private BigDecimal calcuteTotal(OrderCreatedEvent event) {
        logger.info("Caculate total order {}", event.codigoPedido());

        return event.itens()
                .stream()
                .map(item -> item.preco().multiply(BigDecimal.valueOf(item.quantidade())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);

    }
}
