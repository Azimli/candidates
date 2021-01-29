package guavapay.guavapay.service.impl;

import guavapay.guavapay.dao.CardTypeRepository;
import guavapay.guavapay.dto.CardTypeDto;
import guavapay.guavapay.exception.CardsNotFoundException;
import guavapay.guavapay.mapper.CardTypeMapper;
import guavapay.guavapay.model.CardType;
import guavapay.guavapay.service.CardTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CardsTypeServiceImpl implements CardTypeService {

    @Autowired
    private CardTypeRepository cardTypeRepository;

    private CardType cardType;



    @Override
    public List listCards() {
        Iterable<CardType> allCard = cardTypeRepository.findAll();
        return Arrays.asList(allCard);
    }

    @Override
    public CardTypeDto getCards(Long id) {
        CardType byId = cardTypeRepository.findById(id)
                .orElseThrow(()-> new CardsNotFoundException("Cards not found"));

        return CardTypeMapper.INSTANCE.toDTO(byId);
    }

    @Override
    public CardTypeDto createCards(CardTypeDto cardTypeDto) {
        if (cardTypeDto.getId() != null) {
            throw new IllegalArgumentException("Cards id must be null");
        }
        CardType cards = CardTypeMapper.INSTANCE.cardType(cardTypeDto);
        System.out.println("cards = " + cards.getCard_type() + " " + cards.getCodeword());

        return CardTypeMapper.INSTANCE.toDTO(cardTypeRepository.save(cards));
    }

    @Override
    public CardTypeDto updateCards(CardTypeDto cardTypeDto, Long id) {
       cardTypeDto.setId(id);
       CardType cards = CardTypeMapper.INSTANCE.cardType(cardTypeDto);
       return CardTypeMapper.INSTANCE.toDTO(cardTypeRepository.save(cards));
    }

    @Override
    public CardTypeDto deleteCards(Long id) {
        Optional<CardType> byId = cardTypeRepository.findById(id);

        if (byId.isPresent()) {
            cardTypeRepository.deleteById(id);
        }else {
            throw  new IllegalArgumentException("No cards with id");
        }

        CardType cardType = byId.orElseThrow(NoSuchElementException::new);

        return CardTypeMapper.INSTANCE.toDTO(cardType);
    }
}
