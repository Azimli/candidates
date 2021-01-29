package guavapay.guavapay.service;

import guavapay.guavapay.dto.OrdersDto;

import java.util.List;

public interface OrdersService {

    List listOrders();

    OrdersDto getOrders(Long id);

    OrdersDto createOrders(OrdersDto ordersDto);

    OrdersDto updateOrders(OrdersDto ordersDto,Long id);

    OrdersDto deleteOrders(Long id);

}
