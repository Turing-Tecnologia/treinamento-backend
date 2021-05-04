package com.turingtecnologia.albatroz.backendalbatroz.services.interfaces;

import com.turingtecnologia.albatroz.backendalbatroz.model.entities.Cliente;

public interface ClienteService {
    Cliente addCliente(Cliente cliente);
    Cliente findByCpfCliente(String cpf);
}
