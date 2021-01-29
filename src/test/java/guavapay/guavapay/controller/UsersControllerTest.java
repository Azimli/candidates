package guavapay.guavapay.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import guavapay.guavapay.repository.UsersRepository;
import guavapay.guavapay.dto.UsersDto;
import guavapay.guavapay.service.UsersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@WebMvcTest(UsersController.class)
@WithMockUser
public class UsersControllerTest {

    private static final String API = "/users";
    private static final String APIBYID = "/users/{id}";
    private static final String APIALL = "/users/all";

    private static final String ID = "$.id";
    private static final long USER_ID = 1;
    private static final String USR_USERNAME = "shakir.azimli";
    private static final String USR_PASSWORD = "123456";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    private UsersDto usersDto;

    @MockBean
    private UsersService usersService;

    @MockBean
    private UsersRepository usersRepository;

    @BeforeEach
    public void setUp() {
        SecurityContext context = new SecurityContextImpl();

        usersDto = UsersDto.builder().id(USER_ID)
                .username(USR_USERNAME)
                .password(USR_PASSWORD)
                .build();
    }

    @Test
    public void allUsers() throws Exception {
        when(usersService.listUsers()).thenReturn(Collections.emptyList());
        mvc.perform(get(APIALL)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    public void getUsersById() throws Exception {
        when(usersService.getUsers(USER_ID)).thenReturn(usersDto);
        mvc.perform(get(APIBYID,USER_ID)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath(ID).value(usersDto.getId()));
    }


    @Test
    public void deleteUsers() throws Exception{
        mvc.perform(delete(APIBYID,USER_ID)
                .content(objectMapper.writeValueAsString(usersDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath(ID).value(usersDto.getId()));
    }



















}