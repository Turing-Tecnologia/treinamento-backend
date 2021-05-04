package com.turingtecnologia.albatroz.backendalbatroz.model.jpaRepositoy;

import com.turingtecnologia.albatroz.backendalbatroz.model.entities.Cliente;
import com.turingtecnologia.albatroz.backendalbatroz.model.entities.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    Consulta findByIdConsulta(Long idConsulta);

    List<Consulta> findByDataConsultaOrderByIdConsulta(Calendar dataConsulta);

    List<Consulta> findByDataConsultaAndClienteConsulta(Calendar dataConsulta, Cliente clienteConsulta);

    List<Consulta> findByDataConsultaOrderByNumeroFichaConsulta(Calendar dataConsulta);

    List<Consulta> findByClienteConsulta(Cliente cliente);

    Set<Consulta> findByClienteConsultaAndDataConsultaOrderByDataConsultaDesc(Cliente cliente, Calendar geraDataAtual);
}
