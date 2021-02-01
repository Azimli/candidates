package guavapay.guavapay.service.impl;

import guavapay.guavapay.dto.UsersDto;
import guavapay.guavapay.exception.UserNotFoundException;
import guavapay.guavapay.mapper.UsersMapper;
import guavapay.guavapay.model.Users;
import guavapay.guavapay.repository.UsersRepository;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UsersServiceImplTest {

    @InjectMocks
    private UsersServiceImpl userService;

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();


    @Mock
    private UsersRepository userRepository;

    @Spy
    private UsersMapper userMapper;

    private UsersDto usersDto;
    private Users users;


    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp(){

        users = Users
                .builder()
                .id(1L)
                .username("shakir.azimli")
                .password("123456")
                .build();

        usersDto = UsersDto
                .builder()
                .id(1L)
                .username("shakir.azimli")
                .password("123456")
                .build();
    }

    @Test
    void listUsersTest(){

        Users users = new Users(1L,"shakir.azimli","123456");
        when(userRepository.findById(1L)).thenReturn(Optional.of(users));
        UsersDto list = userService.getUsers(1L);
        userService.listUsers();
        assertEquals(users.getId(),list.getId());
        assertEquals(users.getUsername(),list.getUsername());
        assertEquals(users.getPassword(),list.getPassword());
        assertNotEquals(users,usersDto);
        assertNotNull(users);
        assertNotNull(list);
        verify(userRepository).findById(1L);
    }

    @Test
    void getUsersTest() {
        Users users = new Users(1L,"shakir.azimli","123456");
        when(userRepository.findById(1L)).thenReturn(Optional.of(users));
        UsersDto list = userService.getUsers(1L);
        assertEquals(users.getId(),list.getId());
        assertEquals(users.getUsername(),list.getUsername());
        assertEquals(users.getPassword(),list.getPassword());
        assertNotEquals(users,usersDto);
        assertNotNull(users);
        assertNotNull(list);
        verify(userRepository).findById(1L);

    }


    @Test
    @WithMockUser
    public void createUsersTest(){
        Users users = new Users(1L,"shakir.azimli","123456");
        UsersDto expected = usersDto.builder().
                username("shakir.azimli").
                password("123456").build();
        usersDto.setPassword(passwordEncoder.encode(usersDto.getPassword()));
        when(userRepository.save(any())).thenReturn(users);
        UsersDto actual = userService.createUsers(expected);
        assertEquals(expected.getUsername(),actual.getUsername());

    }

    @Test
    public void createUsersTestError(){
        UsersDto dto = UsersDto.builder()
                .id(null)
                .username("shakir.azimli")
                .password("123456").build();

        exceptionRule.expect(UserNotFoundException.class);
        exceptionRule.expectMessage("Users id must be null");
        userService.createUsers(dto);
    }


    @Test
    public void updateUsersTest(){

        Users users = new Users(1L,"shakir.azimli","123456");
        UsersDto expected = usersDto.builder().
                username("shakir.azimli").
                password("123456").build();

        when(passwordEncoder.encode(expected.getPassword())).thenReturn("123456");
        when(userRepository.save(any())).thenReturn(users);
        UsersDto actual = userService.updateUsers(expected,1L);
        assertEquals(expected.getId(),actual.getId());

    }

    @Test
    public void deleteUsersTest() {
        Users users = Users.builder()
                .id(1L)
                .username("shakir.azimli")
                .password("123456")
                .build();
        when(userRepository.findById(1L)).thenReturn(Optional.of(users));
        userService.deleteUsers(1L);
        verify(userRepository).deleteById(users.getId());
        verify(userRepository).findById(users.getId());
    }









}