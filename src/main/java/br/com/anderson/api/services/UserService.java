package br.com.anderson.api.services;

import br.com.anderson.api.domain.Usuario;

import java.util.List;

public interface UserService {

    Usuario findById(Integer id);
    List<Usuario> findAll();
}
