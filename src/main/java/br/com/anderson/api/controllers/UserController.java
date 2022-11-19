package br.com.anderson.api.controllers;

import br.com.anderson.api.domain.Usuario;
import br.com.anderson.api.domain.dtos.UsuarioDTO;
import br.com.anderson.api.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    public static final String ID = "/{id}";
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserService userService;

    @GetMapping(ID)
    public ResponseEntity<UsuarioDTO> findById(@PathVariable Integer id){
        return ResponseEntity.ok().body(modelMapper.map(userService.findById(id), UsuarioDTO.class));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> findAll(){
        return ResponseEntity.ok()
                .body(userService.findAll()
                        .stream().map(usuario -> modelMapper.map(usuario, UsuarioDTO.class)).collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> create(@RequestBody UsuarioDTO obj){
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(ID).buildAndExpand(userService.create(obj).getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(ID)
    public ResponseEntity<UsuarioDTO> update(@PathVariable Integer id, @RequestBody UsuarioDTO obj){
        obj.setId(id);
        return ResponseEntity.ok().body(modelMapper.map(userService.update(obj), UsuarioDTO.class));
    }

    @DeleteMapping(ID)
    public ResponseEntity<UsuarioDTO> delete(@PathVariable Integer id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
