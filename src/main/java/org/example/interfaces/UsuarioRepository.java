package org.example.interfaces;

import org.example.domain.Usuario;

import java.util.List;

public interface UsuarioRepository {
    List<Usuario> findAll();
    Usuario findById(int id);
    void save(Usuario usuario);
    void update(Usuario usuario);
    void delete(int id);
}

