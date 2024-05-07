package org.example.infraestructure.repository.UsuarioRepository;

import org.example.domain.Usuario;
import org.example.interfaces.UsuarioRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UsuarioRepositoryImpl implements UsuarioRepository {
    private static final String FILE_NAME = "usuario.dat";

    @Override
    public List<Usuario> findAll() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (ArrayList<Usuario>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }
    @Override
    public Usuario findById(int id) {
        return findAll().stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }
    @Override
    public void save(Usuario usuario) {
        List<Usuario> usuarios = findAll();
        usuarios.add(usuario);
        saveAll(usuarios);
    }

    @Override
    public void update(Usuario usuario) {
        List<Usuario> usuarios = findAll();
        usuarios = usuarios.stream()
                .map(p -> p.getId() == usuario.getId() ? usuario : p)
                .collect(Collectors.toList());
        saveAll(usuarios);
    }

    @Override
    public void delete(int id) {
        List<Usuario> usuarios = findAll();
        usuarios = usuarios.stream()
                .filter(p -> p.getId() != id)
                .collect(Collectors.toList());
        saveAll(usuarios);
    }

    private void saveAll(List<Usuario> usuarios) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(usuarios);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
