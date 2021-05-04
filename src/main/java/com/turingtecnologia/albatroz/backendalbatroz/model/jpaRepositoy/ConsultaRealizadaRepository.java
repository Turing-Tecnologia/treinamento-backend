package com.turingtecnologia.albatroz.backendalbatroz.model.jpaRepositoy;

import com.turingtecnologia.albatroz.backendalbatroz.model.entities.Cliente;
import com.turingtecnologia.albatroz.backendalbatroz.model.entities.ConsultaRealizada;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Calendar;
import java.util.List;

public interface ConsultaRealizadaRepository extends JpaRepository<ConsultaRealizada, Long> {
    List<ConsultaRealizada> findByClienteConsultaRealizada(Cliente cliente);

    List<ConsultaRealizada> findByDataConsultaRelizadaBetween(Calendar dataInicial, Calendar dataFinal);

    List<ConsultaRealizada> findByDataConsultaRelizadaOrderByNumeroFichaConsultaRelizada(Calendar dataConsultaRealizada);
}
