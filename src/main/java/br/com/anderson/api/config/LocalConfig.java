package br.com.anderson.api.config;

import br.com.anderson.api.domain.Usuario;
import br.com.anderson.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("local")
public class LocalConfig {

    @Autowired
    private UserRepository userRepository;

    @Bean
    public void startDB(){
        Usuario u1 = new Usuario(null, "Anderson", "anderson@gmail.com", "123");
        Usuario u2 = new Usuario(null, "Patrícia", "patricia@gmail.com", "123");

        userRepository.saveAll(List.of(u1, u2));
    }
}
