package br.com.anderson.api.services;

import br.com.anderson.api.domain.User;

public interface UserService {

    User findById(Integer id);
}
