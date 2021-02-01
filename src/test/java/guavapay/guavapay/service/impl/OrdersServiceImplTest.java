package guavapay.guavapay.service.impl;


import guavapay.guavapay.dto.CardTypeDto;
import guavapay.guavapay.dto.OrdersDto;
import guavapay.guavapay.dto.UsersDto;
import guavapay.guavapay.exception.OrdersNotFoundException;
import guavapay.guavapay.mapper.UsersMapper;
import guavapay.guavapay.model.CardType;
import guavapay.guavapay.model.Orders;
import guavapay.guavapay.model.Users;
import guavapay.guavapay.repository.OrderRepository;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrdersServiceImplTest {

    @InjectMocks
    private OrdersServiceImpl ordersService;

    @Mock
    private OrderRepository orderRepository;


    private OrdersDto ordersDto;

    private Orders orders;

    private CardType cardType;

    private CardTypeDto cardTypeDto;

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();




    @BeforeEach
    void setUp(){

        cardType = CardType
                .builder()
                .id(1L)
                .card_type("MS")
                .codeword("shakir")
                .holder_name("Shakir Azimli")
                .period(24)
                .urgent(true)
                .build();

        cardTypeDto = CardTypeDto
                .builder()
                .id(1L)
                .card_type("MS")
                .codeword("shakir")
                .holder_name("Shakir Azimli")
                .period(24)
                .urgent(true)
                .build();



        orders = Orders
                .builder()
                .id(1L)
                .cardType(cardType)
                .creation_time(LocalDate.now())
                .status('1')
                .build();

        ordersDto = OrdersDto.builder()
                .id(1L)
                .cardTypeDto(cardTypeDto)
                .creation_time(LocalDate.now())
                .status('1')
                .build();

    }

    @Test
    void listOrdersTest(){
        Orders orders = new Orders(1L,LocalDate.now(),'1',cardType);
        when(orderRepository.findById(1L)).thenReturn(Optional.of(orders));
        ordersService.listOrders();
        OrdersDto list = ordersService.getOrders(1L);
        assertEquals(orders.getId(),list.getId());
        assertEquals(orders.getStatus(),list.getStatus());
        assertEquals(orders.getCreation_time(),list.getCreation_time());
        assertNotEquals(orders,ordersDto);
        assertNotNull(orders);
        assertNotNull(list);
        verify(orderRepository).findById(1L);

    }

    @Test
    void getOrdersTest() {
        Orders orders = new Orders(1L,LocalDate.now(),'1',cardType);

        when(orderRepository.findById(1L)).thenReturn(Optional.of(orders));
        OrdersDto list = ordersService.getOrders(1L);
        assertEquals(orders.getId(),list.getId());
        assertEquals(orders.getCreation_time(),list.getCreation_time());
        assertEquals(orders.getStatus(),list.getStatus());
        assertNotEquals(orders,ordersDto);
        assertNotNull(orders);
        assertNotNull(list);
        verify(orderRepository).findById(1L);
    }



    @Test
    void createOrdersTest() {

        Orders orders = new Orders(1L,LocalDate.now(),'1',cardType);
        OrdersDto expected = ordersDto.builder().
                creation_time(LocalDate.now()).
                cardTypeDto(cardTypeDto).
                status('1')
                .build();
        when(orderRepository.save(any())).thenReturn(orders);
        OrdersDto actual = ordersService.createOrders(expected);
        assertEquals(expected.getCreation_time(),actual.getCreation_time());

    }

    @Test
    void updateOrdersTest() {
        Orders orders = new Orders(1L,LocalDate.now(),'1',cardType);
        OrdersDto expected = ordersDto.builder().
                creation_time(LocalDate.now()).
                cardTypeDto(cardTypeDto).
                status('1')
                .build();

        when(orderRepository.save(any())).thenReturn(orders);
        OrdersDto actual = ordersService.updateOrders(expected,1L);
        assertEquals(expected.getId(),actual.getId());
    }

    @Test
    void deleteOrders() {
        Orders orders = Orders.builder()
                .id(1L)
                .creation_time(LocalDate.now()).
                cardType(cardType)
                .status('1')
                .build();
        when(orderRepository.findById(1L)).thenReturn(Optional.of(orders));
        ordersService.deleteOrders(1L);
        verify(orderRepository).deleteById(orders.getId());
        verify(orderRepository).findById(orders.getId());
    }









    }