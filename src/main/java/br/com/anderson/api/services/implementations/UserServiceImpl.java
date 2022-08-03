package br.com.anderson.api.services.implementations;

import br.com.anderson.api.domain.Usuario;
import br.com.anderson.api.repositories.UserRepository;
import br.com.anderson.api.services.UserService;
import br.com.anderson.api.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Usuario findById(Integer id) {
        Optional<Usuario> obj = userRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }
}
