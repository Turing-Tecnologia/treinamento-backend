package com.turingtecnologia.albatroz.backendalbatroz.services.interfaces;

import java.util.List;

import com.turingtecnologia.albatroz.backendalbatroz.model.entities.Dentista;

public interface DentistaService {
	
	Dentista addDentista(Dentista dentista);
	
	Dentista findByIdDentista(Long id);
	
	List<Dentista> findAllDentistas();
}
