package br.com.anderson.api.services.implementations;

import br.com.anderson.api.domain.Usuario;
import br.com.anderson.api.domain.dtos.UsuarioDTO;
import br.com.anderson.api.repositories.UserRepository;
import br.com.anderson.api.services.UserService;
import br.com.anderson.api.services.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Usuario findById(Integer id) {
        Optional<Usuario> obj = userRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public List<Usuario> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Usuario create(UsuarioDTO obj) {
        return userRepository.save(modelMapper.map(obj, Usuario.class));
    }
}
