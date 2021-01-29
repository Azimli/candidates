package guavapay.guavapay.controller;

import guavapay.guavapay.dto.CardTypeDto;
import guavapay.guavapay.dto.UsersDto;
import guavapay.guavapay.service.CardTypeService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cards")
@Api(value = "Cards API")
public class CardTypeController {

    @Autowired
    private CardTypeService cardTypeService;

    @GetMapping("all")
    public ResponseEntity allCards() {
        return ResponseEntity.ok(cardTypeService.listCards());
    }

    @GetMapping("{id}")
    public ResponseEntity<CardTypeDto> getCards(@PathVariable("id") Long id) {
        return ResponseEntity.ok(cardTypeService.getCards(id));
    }

    @PostMapping
    public ResponseEntity<CardTypeDto> createCards(@RequestBody CardTypeDto cardTypeDto){
        System.out.println("Cards add" + cardTypeDto);
        return ResponseEntity.ok(cardTypeService.createCards(cardTypeDto));
    }

    @PutMapping("{id}")
    public ResponseEntity<CardTypeDto> updateCards(@RequestBody CardTypeDto cardTypeDto, @PathVariable("id") Long id){
        return ResponseEntity.ok (cardTypeService.updateCards (cardTypeDto,id));
    }

    @DeleteMapping("{id}")
    ResponseEntity<CardTypeDto> deleteCards(@PathVariable("id") Long id, CardTypeDto cardTypeDto){
        cardTypeService.deleteCards (id);
        return ResponseEntity.ok(cardTypeDto);
    }

}
