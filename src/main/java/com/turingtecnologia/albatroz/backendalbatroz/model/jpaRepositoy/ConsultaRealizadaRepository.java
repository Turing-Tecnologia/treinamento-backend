package com.turingtecnologia.albatroz.backendalbatroz.model.jpaRepositoy;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.turingtecnologia.albatroz.backendalbatroz.model.entities.Cliente;
import com.turingtecnologia.albatroz.backendalbatroz.model.entities.ConsultaRealizada;
import com.turingtecnologia.albatroz.backendalbatroz.model.entities.Dentista;

public interface ConsultaRealizadaRepository extends JpaRepository<ConsultaRealizada, Long> {
    List<ConsultaRealizada> findByClienteConsultaRealizada(Cliente cliente);
    
    List<ConsultaRealizada> findByDentistaConsultaRealizada(Dentista dentista);

    List<ConsultaRealizada> findByDataConsultaRelizadaBetween(Calendar dataInicial, Calendar dataFinal);

    List<ConsultaRealizada> findByDataConsultaRelizadaOrderByNumeroFichaConsultaRelizada(Calendar dataConsultaRealizada);
}
