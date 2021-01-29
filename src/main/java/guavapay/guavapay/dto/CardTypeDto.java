package guavapay.guavapay.dto;

import guavapay.guavapay.model.CardType;
import guavapay.guavapay.model.Orders;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardTypeDto {

    private Long id;

    private String card_type;

    private String holder_name;

    private int period;

    private boolean urgent;

    private String codeword;

   // private Collection<Orders> orders;


}
