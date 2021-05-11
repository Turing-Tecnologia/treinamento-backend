package com.turingtecnologia.albatroz.backendalbatroz.services.implementations;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import org.springframework.stereotype.Service;

import com.turingtecnologia.albatroz.backendalbatroz.exceptions.error.ResourceNotFoundException;
import com.turingtecnologia.albatroz.backendalbatroz.model.entities.Consulta;
import com.turingtecnologia.albatroz.backendalbatroz.model.entities.ConsultaRealizada;
import com.turingtecnologia.albatroz.backendalbatroz.model.entities.Dentista;
import com.turingtecnologia.albatroz.backendalbatroz.model.jpaRepositoy.ConsultaRepository;
import com.turingtecnologia.albatroz.backendalbatroz.model.jpaRepositoy.DentistaRepository;
import com.turingtecnologia.albatroz.backendalbatroz.services.interfaces.DentistaService;
import com.turingtecnologia.albatroz.backendalbatroz.model.jpaRepositoy.ConsultaRealizadaRepository;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class DentistaServiceImplementation implements DentistaService {
    private final DentistaRepository dentistaRepository;
    private final ConsultaRealizadaRepository consultaRealizadaRepository;
    private final ConsultaRepository consultaRepository;

    @Override
    public Dentista addDentista(Dentista dentista){
        return dentistaRepository.save(dentista);
    }
    
    @Override
    public List<Dentista> findAllDentistas() {
        List<Dentista> dentistas = dentistaRepository.findAll();
        for (Dentista dentista : dentistas) {
        	dentista.getConsultasRealizadasDentista().clear();
        }
        return dentistaRepository.findAll();
    }

    
    @Override
    public Dentista findByIdDentista(Long id) {
        vericaSeDentistaExiste(id);
        return dentistaRepository.findByIdDentista(id);
    }
    
    @Override
    public Dentista findByCpfDentista(String cpf) {
    	verificaSeDentistaExiste(cpf);
        Dentista dentista = dentistaRepository.findByCpfDentista(cpf);
        dentista.getConsultasDentista().clear();
        dentista.setConsultasDentista(consultaRepository.
            	findByDentistaConsultaAndDataConsultaOrderByDataConsultaDesc(dentista, geraDataAtual()));
        	return dentistaRepository.findByCpfDentista(cpf);
        }

    private void vericaSeDentistaExiste(long id) {
        if (dentistaRepository.findByIdDentista(id) == null) {
            throw new ResourceNotFoundException("Não existe dentista com esse ID: " + id);
        }
    }
    private void verificaSeDentistaExiste(String cpf) {
  	  if (dentistaRepository.findByCpfDentista(cpf) == null) {
  		  throw new ResourceNotFoundException("Não existe dentista com esse CPF: " + cpf);
  	  	}
    }

    @Override
    public void removeDentista(Dentista dentista) {
    	dentista.setIdDentista(dentistaRepository.
                findByCpfDentista(dentista.getCpfDentista()).getIdDentista());
    	verificaSeDentistaExiste(dentista.getCpfDentista());
        removeConsultasDentista(dentista);
        dentistaRepository.delete(dentista);
    }
    
      private void removeConsultasDentista(Dentista dentista) {
          List<Consulta> consultasMarcadas = consultaRepository.findByDentistaConsulta(dentista);
          List<ConsultaRealizada> consultasRealizadas = consultaRealizadaRepository.findByDentistaConsultaRealizada(dentista);
          for (ConsultaRealizada consultaRealizada : consultasRealizadas) {
              consultaRealizadaRepository.delete(consultaRealizada);
          }
          for (Consulta consulta : consultasMarcadas) {
              consultaRepository.delete(consulta);
          }
      }
      
      @Override
      public Dentista alteraDentista(Dentista dentista) {
    	  verificaSeDentistaExiste(dentista.getCpfDentista());
          dentista.setIdDentista(dentistaRepository.findByCpfDentista(dentista.getCpfDentista()).getIdDentista());
          return dentistaRepository.save(dentista);
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
