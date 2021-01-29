package guavapay.guavapay.controller;

import guavapay.guavapay.dto.UsersDto;
import guavapay.guavapay.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping("all")
    public ResponseEntity allUsers() {
        return ResponseEntity.ok(usersService.listUsers());
    }

    @GetMapping("{id}")
    public ResponseEntity<UsersDto> getUsers(@PathVariable("id") Long id) {
        return ResponseEntity.ok(usersService.getUsers(id));
    }

    @PostMapping
    public ResponseEntity<UsersDto> createUsers(@RequestBody UsersDto usersDto){
        System.out.println("Users add" + usersDto);
        return ResponseEntity.ok(usersService.createUsers(usersDto));
    }

    @PutMapping("{id}")
    public ResponseEntity<UsersDto> updateUsers(@RequestBody UsersDto usersDto, @PathVariable("id") Long id){
        return ResponseEntity.ok (usersService.updateUsers (usersDto,id));
    }

    @DeleteMapping("{id}")
    ResponseEntity<UsersDto> deleteUsers(@PathVariable("id") Long id, UsersDto usersDto){
        usersService.deleteUsers (id);
        return ResponseEntity.ok(usersDto);
    }

}
