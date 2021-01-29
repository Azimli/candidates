package guavapay.guavapay.mapper;


import guavapay.guavapay.dto.OrdersDto;
import guavapay.guavapay.model.Orders;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface OrdersMapper {

    public static final OrdersMapper INSTANCE = Mappers.getMapper(OrdersMapper.class);

    @Mappings({
            @Mapping(target = "creation_time",source = "orders.creation_time"),
            @Mapping(target = "status",source = "orders.status")
    })
    OrdersDto toDTO(Orders orders);


    @Mappings({
            @Mapping(target = "creation_time",source = "ordersDto.creation_time"),
            @Mapping(target = "status",source = "ordersDto.status")
    })
    Orders orders(OrdersDto ordersDto);

}
