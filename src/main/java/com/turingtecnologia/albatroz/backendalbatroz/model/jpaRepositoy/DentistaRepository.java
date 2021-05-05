package com.turingtecnologia.albatroz.backendalbatroz.model.jpaRepositoy;


import com.turingtecnologia.albatroz.backendalbatroz.model.entities.Dentista;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DentistaRepository extends JpaRepository<Dentista, Long> {

	Dentista findByIdDentista(Long idDentista);

}
