package br.com.anderson.api.services;

import br.com.anderson.api.domain.Usuario;

public interface UserService {

    Usuario findById(Integer id);
}
