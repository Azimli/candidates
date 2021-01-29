package guavapay.guavapay.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
}
