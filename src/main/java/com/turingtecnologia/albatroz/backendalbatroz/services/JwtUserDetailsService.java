package com.turingtecnologia.albatroz.backendalbatroz.services;

import java.util.ArrayList;

import com.turingtecnologia.albatroz.backendalbatroz.model.entities.Usuario;
import com.turingtecnologia.albatroz.backendalbatroz.dto.UsuarioDTO;
import com.turingtecnologia.albatroz.backendalbatroz.model.jpaRepositoy.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByUsername(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("Username not found: " + username);
        } else {
            return new org.springframework.security.core.userdetails.User(usuario.getUsername(),
                    usuario.getPassword(),
                    new ArrayList<>());
        }
    }

    public Usuario save(UsuarioDTO user) {
        Usuario usuario = new Usuario();
        usuario.setNomeCompleto(user.getNomeCompleto());
        usuario.setUsername(user.getUsername());
        usuario.setPassword(passwordEncoder.encode(user.getPassword()));
        return usuarioRepository.save(usuario);
    }
}