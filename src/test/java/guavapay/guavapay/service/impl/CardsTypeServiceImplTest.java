package guavapay.guavapay.service.impl;

import guavapay.guavapay.dto.CardTypeDto;
import guavapay.guavapay.dto.OrdersDto;
import guavapay.guavapay.model.CardType;
import guavapay.guavapay.model.Orders;
import guavapay.guavapay.repository.CardTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CardsTypeServiceImplTest{

    @InjectMocks
    private CardsTypeServiceImpl cardsTypeService;

    @Mock
    private CardTypeRepository cardTypeRepository;

    private CardType cardType;

    private CardTypeDto cardTypeDto;


    @BeforeEach
    void setUp(){

        cardType = CardType
                .builder()
                .id(1L)
                .card_type("MS")
                .codeword("shakir")
                .holder_name("Shakir Azimli")
                .period(24)
                .urgent(true)
                .build();

        cardTypeDto = CardTypeDto
                .builder()
                .id(1L)
                .card_type("MS")
                .codeword("shakir")
                .holder_name("Shakir Azimli")
                .period(24)
                .urgent(true)
                .build();
    }

    @Test
    public void listCardsTest() {
        CardType cardType = new CardType(1L,"MS","Shakir Azimli",24,true,"shakir");
        when(cardTypeRepository.findById(1L)).thenReturn(Optional.of(cardType));
        cardsTypeService.listCards();
        CardTypeDto list = cardsTypeService.getCards(1L);
        assertEquals(cardType.getId(),list.getId());
        assertEquals(cardType.getCodeword(),list.getCodeword());
        assertEquals(cardType.getHolder_name(),list.getHolder_name());
        assertNotEquals(cardType,cardTypeDto);
        assertNotNull(cardType);
        assertNotNull(list);
        verify(cardTypeRepository).findById(1L);
    }

    @Test
    void getCardsTest() {
        CardType cardType = new CardType(1L,"MS","Shakir Azimli",24,true,"shakir");

        when(cardTypeRepository.findById(1L)).thenReturn(Optional.of(cardType));
        CardTypeDto list = cardsTypeService.getCards(1L);
        assertEquals(cardType.getId(),list.getId());
        assertEquals(cardType.getCodeword(),list.getCodeword());
        assertEquals(cardType.getHolder_name(),list.getHolder_name());
        assertNotEquals(cardType,cardTypeDto);
        assertNotNull(cardType);
        assertNotNull(list);
        verify(cardTypeRepository).findById(1L);
    }

    @Test
    void createCardsTest() {

        CardType cardType = new CardType(1L,"MS","Shakir Azimli",24,true,"shakir");
        CardTypeDto expected = cardTypeDto.builder().
                card_type("MS").
                codeword("shakir").
                holder_name("Shakir Azimli")
                .period(24)
                .urgent(true)
                .build();
        when(cardTypeRepository.save(any())).thenReturn(cardType);
        CardTypeDto actual = cardsTypeService.createCards(expected);
        assertEquals(expected.getCard_type(),actual.getCard_type());
    }

    @Test
    void updateCardsTest() {
        CardType cardType = new CardType(1L,"MS","Shakir Azimli",24,true,"shakir");
        CardTypeDto expected = cardTypeDto.builder().
                card_type("MS").
                codeword("shakir").
                holder_name("Shakir Azimli")
                .period(24)
                .urgent(true)
                .build();

        when(cardTypeRepository.save(any())).thenReturn(cardType);
        CardTypeDto actual = cardsTypeService.updateCards(expected,1L);
        assertEquals(expected.getId(),actual.getId());
    }

    @Test
    void deleteCardsTest() {
        CardType expected = cardType.builder().
                card_type("MS").
                codeword("shakir").
                holder_name("Shakir Azimli")
                .period(24)
                .urgent(true)
                .build();
        when(cardTypeRepository.findById(1L)).thenReturn(Optional.of(cardType));
        cardsTypeService.deleteCards(1L);
        verify(cardTypeRepository).deleteById(cardType.getId());
        verify(cardTypeRepository).findById(cardType.getId());
    }








}