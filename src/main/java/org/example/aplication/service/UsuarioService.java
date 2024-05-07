package org.example.aplication.service;

import org.example.domain.Usuario;

import java.util.List;

public interface UsuarioService {
        List<Usuario> findAll();
        Usuario findById(int id);
        void save(Usuario usuario);
        void update(Usuario usuario);
        void delete(int id);
}
