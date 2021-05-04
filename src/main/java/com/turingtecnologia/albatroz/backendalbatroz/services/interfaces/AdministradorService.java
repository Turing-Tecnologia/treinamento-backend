package com.turingtecnologia.albatroz.backendalbatroz.services.interfaces;

import com.turingtecnologia.albatroz.backendalbatroz.model.entities.Cliente;
import com.turingtecnologia.albatroz.backendalbatroz.model.entities.Consulta;
import com.turingtecnologia.albatroz.backendalbatroz.model.entities.ConsultaRealizada;

import java.util.List;

public interface AdministradorService {
    List<Cliente> findAllClientes();

    void removeCliente(Cliente cliente);

    Cliente alteraCliente(Cliente cliente);

    Consulta marcaCheckIn(Long idConsulta);

    void marcaCheckOut(Long idConsulta);

    List<Consulta> findAllConsultas();

    List<ConsultaRealizada> findAllConsultasRealizadas();

    List<Cliente> findConsultasPorData(String data);

    List<Cliente> findConsultasDoDia();

    List<ConsultaRealizada> findConsultasRealizadasPorPeriodo(String dataInicial, String dataFinal);
}
