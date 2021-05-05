package com.turingtecnologia.albatroz.backendalbatroz.services.interfaces;

import com.turingtecnologia.albatroz.backendalbatroz.model.entities.Dentista;

public interface DentistaService {
	Dentista addDentista(Dentista dentista);
	Dentista findByCpfDentista(String cpf);
}
