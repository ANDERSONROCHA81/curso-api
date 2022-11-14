package br.com.anderson.api.services.implementations;

import br.com.anderson.api.domain.Usuario;
import br.com.anderson.api.domain.dtos.UsuarioDTO;
import br.com.anderson.api.repositories.UserRepository;
import br.com.anderson.api.services.exceptions.DataIntegratyViolationException;
import br.com.anderson.api.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceImplTest {

    public static final Integer ID = 1;
    public static final String NAME = "Anderson";
    public static final String EMAIL = "anderson@gmail.com";
    public static final String PASSWORD = "123";
    public static final String OBJETO_NAO_ENCONTRADO = "Objeto não encontrado";
    public static final int INDEX = 0;
    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    private Usuario usuario;

    private UsuarioDTO usuarioDTO;

    private Optional<Usuario> optionalUsuario;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void whenFindByIdThenReturnAnUserInstance() {
        when(userRepository.findById(anyInt())).thenReturn(optionalUsuario);

        Usuario response = userService.findById(ID);

        assertNotNull(response);
        assertEquals(Usuario.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
    }

    @Test
    void whenFindByIdThenReturnAnObjectNotFoundException() {
        when(userRepository.findById(anyInt())).thenThrow(new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO));

        try{
            userService.findById(ID);
        }catch (Exception ex){
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(OBJETO_NAO_ENCONTRADO, ex.getMessage());
        }
    }

    @Test
    void WhenFindAllThenReturnAnListOfUsers() {
        when(userRepository.findAll()).thenReturn(List.of(usuario));

        List<Usuario> response = userService.findAll();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(Usuario.class, response.get(INDEX).getClass());

        assertEquals(ID, response.get(INDEX).getId());
        assertEquals(NAME, response.get(INDEX).getName());
        assertEquals(EMAIL, response.get(INDEX).getEmail());
        assertEquals(PASSWORD, response.get(INDEX).getPassword());
    }

    @Test
    void whenCreateThenReturnSuccess() {
        when(userRepository.save(any())).thenReturn(usuario);

        Usuario response = userService.create(usuarioDTO);

        assertNotNull(response);
        assertEquals(Usuario.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());
    }

    @Test
    void whenCreateThenReturnAnDataIntegrityViolationException() {
        when(userRepository.findByEmail(anyString())).thenReturn(optionalUsuario);

        try {
            optionalUsuario.get().setId(2);
            userService.create(usuarioDTO);
        }catch (Exception ex){
            assertEquals(DataIntegratyViolationException.class, ex.getClass());
            assertEquals("E-mail já cadastrado no sistema", ex.getMessage());
        }
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
        optionalUsuario = Optional.of(new Usuario(ID, NAME, EMAIL, PASSWORD));
    }
}