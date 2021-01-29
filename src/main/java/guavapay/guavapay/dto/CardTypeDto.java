package guavapay.guavapay.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import guavapay.guavapay.model.CardType;
import guavapay.guavapay.model.Orders;
import lombok.*;

import java.util.Collection;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardTypeDto {

    @JsonIgnore
    private Long id;

    private String card_type;

    private String holder_name;

    private int period;

    private boolean urgent;

    private String codeword;

   // private Collection<Orders> orders;


}
