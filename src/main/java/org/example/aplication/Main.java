package org.example.aplication;
import org.example.aplication.service.UsuarioService;
import org.example.aplication.service.UsuarioServiceImpl;
import org.example.domain.Usuario;

import org.example.infraestructure.repository.UsuarioRepository.UsuarioRepositoryImpl;
import org.example.interfaces.UsuarioRepository;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final UsuarioService usuarioService;

    static {
        UsuarioRepository usuarioRepository = new UsuarioRepositoryImpl();
        usuarioService = new UsuarioServiceImpl(usuarioRepository);
    }

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            System.out.println("1. Registrar Usuario");
            System.out.println("2. Actualizar usuario");
            System.out.println("3. Borrar usuario");
            System.out.println("4. Mostrar usuarios registrados");
            System.out.println("0. Exit");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    registrarUsuario();
                    break;
                case 2:
                    actualizarUsuario();
                    break;
                case 3:
                    borrarUsuario();
                    break;
                case 4:
                    mostrarUsuarios();
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid Option");
            }
        }
    }

    private static void registrarUsuario(){
        System.out.println("Ingresa el nombre: ");
        String nombre = scanner.nextLine();
        System.out.println("Ingresa el email: ");
        String email = scanner.nextLine();
        System.out.println("Ingresa el rol: ");
        String rol = scanner.nextLine();

        List<Usuario> usuarios = usuarioService.findAll();
        Usuario usuario = new Usuario(usuarios.size(), nombre, email, rol);

        try {
            usuarioService.save(usuario);
            System.out.println("El usuario ya está registrado");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void actualizarUsuario() {
        System.out.println("Ingresa el id del usuario:");
        int id = scanner.nextInt();
        scanner.nextLine();

        Usuario usuario = usuarioService.findById(id);
        if (usuario == null) {
            System.out.println("No se encontró ningún usuario con este id");
            return;
        }

        System.out.println("Ingresa el nuevo nombre del usuario: ");
        String nombre = scanner.nextLine();
        if (!nombre.isEmpty()) {
            usuario.setNombre(nombre);
        }

        System.out.println("Ingresa el nuevo email del usuario");
        String email = scanner.nextLine();
        if (!email.isEmpty()) {
            usuario.setEmail(email);
        }

        System.out.println("Ingresa el nuevo rol del usuario: ");
        String rol = scanner.nextLine();
        if (!rol.isEmpty()) {
            usuario.setRol(rol);
        }

        try {
            usuarioService.update(usuario);
            System.out.println("Usuario actualizado");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void borrarUsuario(){
        System.out.println("Ingresa el id del usuario que deseas borrar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Usuario usuario = usuarioService.findById(id);
        if (usuario == null) {
            System.out.println("No se encontró ningún usuario con este id");
            return;
        }

        usuarioService.delete(id);
        System.out.println("Usuario borrado");
    }

    private static void mostrarUsuarios() {
        List<Usuario> usuarios = usuarioService.findAll();
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados");
        } else {
            System.out.println("Usuarios Registrados: ");
            for (Usuario usuario : usuarios) {
                System.out.println(usuario);
            }
        }
    }
}



