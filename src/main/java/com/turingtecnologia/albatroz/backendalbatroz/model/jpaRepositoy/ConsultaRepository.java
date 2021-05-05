package com.turingtecnologia.albatroz.backendalbatroz.model.jpaRepositoy;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.turingtecnologia.albatroz.backendalbatroz.model.entities.Cliente;
import com.turingtecnologia.albatroz.backendalbatroz.model.entities.Consulta;
import com.turingtecnologia.albatroz.backendalbatroz.model.entities.Dentista;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    Consulta findByIdConsulta(Long idConsulta);

    List<Consulta> findByDataConsultaOrderByIdConsulta(Calendar dataConsulta);

    List<Consulta> findByDataConsultaAndClienteConsulta(Calendar dataConsulta, Cliente clienteConsulta);

    List<Consulta> findByDataConsultaOrderByNumeroFichaConsulta(Calendar dataConsulta);

    List<Consulta> findByClienteConsulta(Cliente cliente);

    Set<Consulta> findByClienteConsultaAndDataConsultaOrderByDataConsultaDesc(Cliente cliente, Calendar geraDataAtual);
    
    Set<Consulta> findByDentistaConsultaAndDataConsultaOrderByDataConsultaDesc(Dentista dentista, Calendar geraDataAtual);
}
