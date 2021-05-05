package com.turingtecnologia.albatroz.backendalbatroz.services.implementations;

import java.util.Calendar;
import java.util.TimeZone;

import org.springframework.stereotype.Service;

import com.turingtecnologia.albatroz.backendalbatroz.exceptions.error.ResourceNotFoundException;
import com.turingtecnologia.albatroz.backendalbatroz.model.entities.Dentista;
import com.turingtecnologia.albatroz.backendalbatroz.model.jpaRepositoy.ConsultaRepository;
import com.turingtecnologia.albatroz.backendalbatroz.model.jpaRepositoy.DentistaRepository;
import com.turingtecnologia.albatroz.backendalbatroz.services.interfaces.DentistaService;

import lombok.AllArgsConstructor;
@SuppressWarnings("unused")
@Service
@AllArgsConstructor
public class DentistaServiceImplementation implements DentistaService {
    final DentistaRepository repository;

    final ConsultaRepository consultaRepository;

    @Override
    public Dentista addDentista(Dentista dentista){
        return repository.save(dentista);
    }

    @Override
    public Dentista findByCpfDentista(String cpf) {
        verificaSeDentistaExiste(cpf);
        Dentista dentista = repository.findByCpfDentista(cpf);
        dentista.getConsultasDentista().clear();
        dentista.setConsultasDentista(consultaRepository.
        		findByDentistaConsultaAndDataConsultaOrderByDataConsultaDesc(dentista, geraDataAtual()));
        return repository.findByCpfDentista(cpf);
    }

    private void verificaSeDentistaExiste(String cpf) {
        if (repository.findByCpfDentista(cpf) == null) {
            throw new ResourceNotFoundException("Não existe dentista com esse CPF: " + cpf);
        }
    }

    private Calendar geraDataAtual() {
        Calendar data = Calendar.getInstance();
        data.clear();
        TimeZone timeZone = TimeZone.getTimeZone("America/Sao_Paulo");
        TimeZone.setDefault(timeZone);
        data.set(Calendar.YEAR, Calendar.getInstance(timeZone).get(Calendar.YEAR));
        data.set(Calendar.MONTH, Calendar.getInstance(timeZone).get(Calendar.MONTH));
        data.set(Calendar.DAY_OF_MONTH, Calendar.getInstance(timeZone).get(Calendar.DAY_OF_MONTH));
        data.set(Calendar.HOUR_OF_DAY, 0);
        data.set(Calendar.MINUTE, 0);
        data.set(Calendar.SECOND, 0);
        data.set(Calendar.MILLISECOND, 0);
        return data;
    }
}
