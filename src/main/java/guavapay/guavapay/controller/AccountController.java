package guavapay.guavapay.controller;

import guavapay.guavapay.model.Orders;
import guavapay.guavapay.service.AlphanumericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("account")
public class AccountController {

    @Autowired
    private AlphanumericService alphanumericService;

    @PostMapping("{id}")
    public String genearteAlphanumeric(@PathVariable Long id){

        return alphanumericService.generatealphanumeric(id);
    }

    @PostMapping
    public String genearteCardNumber(){
        return alphanumericService.generateCardNumber();
    }

}
