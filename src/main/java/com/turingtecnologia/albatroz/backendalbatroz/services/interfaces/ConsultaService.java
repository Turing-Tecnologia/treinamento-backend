package com.turingtecnologia.albatroz.backendalbatroz.services.interfaces;

import com.turingtecnologia.albatroz.backendalbatroz.model.entities.Consulta;

public interface ConsultaService {
    Consulta alteraConsulta(Consulta consulta);

    Consulta addConsulta(Consulta consulta);

    Consulta findByIdConsulta(Long id);

    void remove(Long id);
}
