package com.example.travel;

import com.example.travel.model.Role;
import com.example.travel.model.Usuario;
import com.example.travel.repository.RoleRepository;
import com.example.travel.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class TravelApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TravelApiApplication.class, args);
    }

    @Bean
    CommandLineRunner initData(RoleRepository roleRepository,
                               UsuarioRepository usuarioRepository,
                               PasswordEncoder passwordEncoder) {
        return args -> {
            Role adminRole = roleRepository.findByNome("ROLE_ADMIN")
                    .orElseGet(() -> roleRepository.save(new Role("ROLE_ADMIN")));
            Role userRole = roleRepository.findByNome("ROLE_USER")
                    .orElseGet(() -> roleRepository.save(new Role("ROLE_USER")));

            if (usuarioRepository.findByUsername("admin").isEmpty()) {
                Usuario admin = new Usuario("admin", passwordEncoder.encode("123456"));
                admin.getRoles().add(adminRole);
                usuarioRepository.save(admin);
            }

            if (usuarioRepository.findByUsername("cliente").isEmpty()) {
                Usuario cliente = new Usuario("cliente", passwordEncoder.encode("123456"));
                cliente.getRoles().add(userRole);
                usuarioRepository.save(cliente);
            }
        };
    }
}
