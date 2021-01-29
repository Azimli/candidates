package guavapay.guavapay.service.impl;

import guavapay.guavapay.dao.UsersRepository;
import guavapay.guavapay.dto.UsersDto;
import guavapay.guavapay.exception.UserNotFoundException;
import guavapay.guavapay.mapper.UsersMapper;
import guavapay.guavapay.model.Users;
import guavapay.guavapay.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    private Users users;

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;



    @Override
    public List listUsers() {
        Iterable<Users> allUsers = usersRepository.findAll();
        return Arrays.asList(allUsers);
    }

    @Override
    public UsersDto getUsers(Long id) {
        Users byId = usersRepository.findById(id)
                .orElseThrow(()-> new UserNotFoundException("User not found"));

        return UsersMapper.INSTANCE.toDTO(byId);
    }

    @Override
    public UsersDto createUsers(UsersDto usersDto) {

        if (usersDto.getId() != null){
            throw new IllegalArgumentException("Users id must be null");
        }
        usersDto.setPassword(passwordEncoder.encode(usersDto.getPassword()));

        Users users = UsersMapper.INSTANCE.users(usersDto);
        System.out.println("user = " + users.getPassword());
        return UsersMapper.INSTANCE.toDTO(usersRepository.save(users));
    }

    @Override
    public UsersDto updateUsers(UsersDto usersDto, Long id) {
        usersDto.setId(id);
        usersDto.setPassword(passwordEncoder.encode(usersDto.getPassword()));
        Users users = UsersMapper.INSTANCE.users(usersDto);

        return UsersMapper.INSTANCE.toDTO(usersRepository.save(users));
    }

    @Override
    public UsersDto deleteUsers(Long id) {
        Optional<Users> byid = usersRepository.findById(id);
        if (byid.isPresent()){
            usersRepository.deleteById(id);
        }else {
            throw  new IllegalArgumentException("No users with id");
        }
        Users usersDTO = byid.orElseThrow(NoSuchElementException::new);
        return UsersMapper.INSTANCE.toDTO(usersDTO);
    }

    @Override
    public UserDetails loadUserByUsername(String username){

        Users user = usersRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid username"));

        return user;
    }
}
