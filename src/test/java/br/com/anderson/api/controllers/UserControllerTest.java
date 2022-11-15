package br.com.anderson.api.controllers;

import br.com.anderson.api.domain.Usuario;
import br.com.anderson.api.domain.dtos.UsuarioDTO;
import br.com.anderson.api.services.implementations.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserControllerTest {

    public static final Integer ID = 1;
    public static final String NAME = "Anderson";
    public static final String EMAIL = "anderson@gmail.com";
    public static final String PASSWORD = "123";
    public static final int INDEX = 0;

    private Usuario usuario;
    private UsuarioDTO usuarioDTO;

    @InjectMocks
    private UserController userController;
    @Mock
    private UserServiceImpl userService;
    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void WhenFindByIdThenReturnSuccess() {
        when(userService.findById(anyInt())).thenReturn(usuario);
        when(modelMapper.map(any(), any())).thenReturn(usuarioDTO);

        ResponseEntity<UsuarioDTO> response = userController.findById(ID);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(UsuarioDTO.class, response.getBody().getClass());

        assertEquals(ID, response.getBody().getId());
        assertEquals(NAME, response.getBody().getName());
        assertEquals(EMAIL, response.getBody().getEmail());
        assertEquals(PASSWORD, response.getBody().getPassword());
    }

    @Test
    void findAll() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    private void startUser(){
        usuario = new Usuario(ID, NAME, EMAIL, PASSWORD);
        usuarioDTO = new UsuarioDTO(ID, NAME, EMAIL, PASSWORD);
    }
}