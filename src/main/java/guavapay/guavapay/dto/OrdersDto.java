package guavapay.guavapay.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrdersDto {

    private Long id;

    private LocalDate creation_time;

    private char status;
}
