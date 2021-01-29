package guavapay.guavapay.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import guavapay.guavapay.model.CardType;
import guavapay.guavapay.model.Orders;
import lombok.*;

import java.time.LocalDate;
import java.util.Collection;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrdersDto {

    private Long id;

    private LocalDate creation_time;

    private char status;

    private CardTypeDto cardTypeDto;

}
