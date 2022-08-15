package br.com.anderson.api.services;

import br.com.anderson.api.domain.Usuario;
import br.com.anderson.api.domain.dtos.UsuarioDTO;

import java.util.List;

public interface UserService {

    Usuario findById(Integer id);
    List<Usuario> findAll();
    Usuario create(UsuarioDTO obj);
    Usuario update(UsuarioDTO obj);
}
