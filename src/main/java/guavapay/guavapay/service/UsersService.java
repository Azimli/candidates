package guavapay.guavapay.service;

import guavapay.guavapay.dto.UsersDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UsersService extends UserDetailsService {

    List listUsers();

    UsersDto getUsers(Long id);

    UsersDto createUsers(UsersDto usersDto);

    UsersDto updateUsers(UsersDto usersDto,Long id);

    UsersDto deleteUsers(Long id);
}
