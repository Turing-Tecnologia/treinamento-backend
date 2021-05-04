package com.turingtecnologia.albatroz.backendalbatroz.model.jpaRepositoy;

import com.turingtecnologia.albatroz.backendalbatroz.model.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Cliente findByCpfCliente(String cpfCliente);

}
