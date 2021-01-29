package guavapay.guavapay.controller;

import guavapay.guavapay.dto.OrdersDto;
import guavapay.guavapay.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;


    @GetMapping("all")
    public ResponseEntity allOrders() {
        return ResponseEntity.ok(ordersService.listOrders());
    }

    @GetMapping("{id}")
    public ResponseEntity<OrdersDto> getOrders(@PathVariable("id") Long id) {
        return ResponseEntity.ok(ordersService.getOrders(id));
    }

    @PostMapping
    public ResponseEntity<OrdersDto> createOrders(@RequestBody OrdersDto ordersDto){
        System.out.println("Orders add" + ordersDto);
        return ResponseEntity.ok(ordersService.createOrders(ordersDto));
    }

    @PutMapping("{id}")
    public ResponseEntity<OrdersDto> updateOrders(@RequestBody OrdersDto ordersDto, @PathVariable("id") Long id){
        return ResponseEntity.ok (ordersService.updateOrders (ordersDto,id));
    }

    @DeleteMapping("{id}")
    ResponseEntity<OrdersDto> deleteOrders(@PathVariable("id") Long id, OrdersDto ordersDto){
        ordersService.deleteOrders(id);
        return ResponseEntity.ok(ordersDto);
    }










}
