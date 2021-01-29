package guavapay.guavapay.service;

import guavapay.guavapay.dto.CardTypeDto;
import guavapay.guavapay.dto.UsersDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardTypeService {

    List listCards();

    CardTypeDto getCards(Long id);

    CardTypeDto createCards(CardTypeDto cardTypeDto);

    CardTypeDto updateCards(CardTypeDto cardTypeDto,Long id);

    CardTypeDto deleteCards(Long id);
}
