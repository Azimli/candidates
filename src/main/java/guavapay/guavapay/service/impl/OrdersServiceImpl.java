package guavapay.guavapay.service.impl;

import guavapay.guavapay.dto.OrdersDto;
import guavapay.guavapay.exception.OrdersNotFoundException;
import guavapay.guavapay.mapper.OrdersMapper;
import guavapay.guavapay.model.Orders;
import guavapay.guavapay.repository.OrderRepository;
import guavapay.guavapay.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrderRepository orderRepository;

    private Orders orders;

    @Override
    public List listOrders() {
        Iterable<Orders> allOrders = orderRepository.findAll();
        return Arrays.asList(allOrders);
    }

    @Override
    public OrdersDto getOrders(Long id) {
        Orders byId = orderRepository.findById(id)
                .orElseThrow(()-> new OrdersNotFoundException("Cards not found"));
        return OrdersMapper.INSTANCE.toDTO(byId);
    }

    @Override
    public OrdersDto createOrders(OrdersDto ordersDto) {
        if (ordersDto.getId() != null){
            throw new IllegalArgumentException("Orders id must be null");
        }
        Orders orders = OrdersMapper.INSTANCE.orders(ordersDto);
        orders.setCreation_time(LocalDate.now());
        System.out.println("orders = " + orders.getId() + " " +
                orders.getStatus() + " " + orders.getCreation_time());
        return OrdersMapper.INSTANCE.toDTO(orderRepository.save(orders));
    }

    @Override
    public OrdersDto updateOrders(OrdersDto ordersDto, Long id) {
        ordersDto.setId(id);
        Orders orders = OrdersMapper.INSTANCE.orders(ordersDto);
        return OrdersMapper.INSTANCE.toDTO(orderRepository.save(orders));
    }

    @Override
    public OrdersDto deleteOrders(Long id) {
        Optional<Orders> byId = orderRepository.findById(id);

        if (byId.isPresent()){
            orderRepository.deleteById(id);
        }else {
            throw  new IllegalArgumentException("No orders with id");
        }
        Orders orders = byId.orElseThrow(NoSuchElementException::new);

        return OrdersMapper.INSTANCE.toDTO(orders);
    }
}
