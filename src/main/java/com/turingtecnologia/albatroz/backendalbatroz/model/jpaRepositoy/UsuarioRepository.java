package com.turingtecnologia.albatroz.backendalbatroz.model.jpaRepositoy;

import com.turingtecnologia.albatroz.backendalbatroz.model.entities.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
    Usuario findByUsername(String username);
}
