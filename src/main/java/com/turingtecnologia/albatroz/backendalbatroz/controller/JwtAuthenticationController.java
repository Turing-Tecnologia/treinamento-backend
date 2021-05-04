package com.turingtecnologia.albatroz.backendalbatroz.controller;


import javax.validation.Valid;

import com.turingtecnologia.albatroz.backendalbatroz.config.JwtTokenUtil;
import com.turingtecnologia.albatroz.backendalbatroz.model.entities.JwtRequest;
import com.turingtecnologia.albatroz.backendalbatroz.model.entities.JwtResponse;
import com.turingtecnologia.albatroz.backendalbatroz.dto.UsuarioDTO;
import com.turingtecnologia.albatroz.backendalbatroz.services.JwtUserDetailsService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class JwtAuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenUtil jwtTokenUtil;

    private final JwtUserDetailsService userDetailsService;

    @ApiOperation(value = "Realização de login, o usuário enviará os seus dados em campos como" +
            ": username e password. o sistema enviará um token que será responsavel por gerenciar" +
            "a autenticação")
    @RequestMapping(value = "/adminlogin", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@Valid @RequestBody JwtRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @ApiOperation(value = "Cria um usuario no banco de dados enviando o json com os parametros username e password")
    @RequestMapping(value = "/signUpAdmin", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@Valid @RequestBody UsuarioDTO user) throws Exception {
        return ResponseEntity.ok(userDetailsService.save(user));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
