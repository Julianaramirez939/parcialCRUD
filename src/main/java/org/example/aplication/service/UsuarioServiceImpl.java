package org.example.aplication.service;

import org.example.domain.Usuario;
import org.example.interfaces.UsuarioRepository;

import java.util.List;


import org.example.domain.Usuario;
import org.example.interfaces.UsuarioRepository;

import java.util.List;

public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario findById(int id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public void save(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    @Override
    public void update(Usuario usuario) {
        usuarioRepository.update(usuario);
    }

    @Override
    public void delete(int id) {
        usuarioRepository.delete(id);
    }
}
