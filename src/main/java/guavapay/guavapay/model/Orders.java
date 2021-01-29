package guavapay.guavapay.model;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate creation_time;

    private char status;

    @ManyToOne
    @JoinColumn(name="cards_id")
    private CardType cardType;

}
