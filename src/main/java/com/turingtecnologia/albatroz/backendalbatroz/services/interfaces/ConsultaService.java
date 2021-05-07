package com.turingtecnologia.albatroz.backendalbatroz.services.interfaces;

import java.util.List;
import java.util.Set;

import com.turingtecnologia.albatroz.backendalbatroz.dto.InfoConsultaDTO;
import com.turingtecnologia.albatroz.backendalbatroz.model.entities.Consulta;

public interface ConsultaService {
    Consulta alteraConsulta(Consulta consulta);

    Consulta addConsulta(Consulta consulta);

    Consulta findByIdConsulta(Long id);

    void remove(Long id);

    List<InfoConsultaDTO> converter(Set<Consulta> consultas);
}
