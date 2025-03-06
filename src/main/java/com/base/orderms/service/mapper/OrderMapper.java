package com.base.orderms.service.mapper;

import com.base.orderms.entity.OrderEntity;
import com.base.orderms.entity.OrderItem;
import com.base.orderms.listener.dto.OrderCreatedEvent;

import java.math.BigDecimal;
import java.util.List;

public class OrderMapper {

    public OrderEntity mapperOrderEntity (OrderCreatedEvent orderCreatedEvent, BigDecimal totalCalculated) {
        OrderEntity entity = new OrderEntity();
        entity.setOrderId(orderCreatedEvent.codigoPedido());
        entity.setCustomerId(orderCreatedEvent.codigoCliente());
        entity.setTotal(totalCalculated);
        entity.setItems(getOrgerItemsMapper(orderCreatedEvent));

        return entity;
    }

    private static List<OrderItem> getOrgerItemsMapper(OrderCreatedEvent orderCreatedEvent) {

        return orderCreatedEvent.itens().stream()
                .map(item -> new OrderItem(item.produto(), item.quantidade(), item.preco()))
                .toList();
    }
}
