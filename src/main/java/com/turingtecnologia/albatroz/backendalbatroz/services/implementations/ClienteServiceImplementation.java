package com.turingtecnologia.albatroz.backendalbatroz.services.implementations;

import com.turingtecnologia.albatroz.backendalbatroz.exceptions.error.ResourceNotFoundException;
import com.turingtecnologia.albatroz.backendalbatroz.model.entities.Cliente;
import com.turingtecnologia.albatroz.backendalbatroz.model.jpaRepositoy.ClienteRepository;
import com.turingtecnologia.albatroz.backendalbatroz.model.jpaRepositoy.ConsultaRepository;
import com.turingtecnologia.albatroz.backendalbatroz.services.interfaces.ClienteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.TimeZone;
@SuppressWarnings("unused")
@Service
@AllArgsConstructor
public class ClienteServiceImplementation implements ClienteService {
    final ClienteRepository repository;

    final ConsultaRepository consultaRepository;

    @Override
    public Cliente addCliente(Cliente cliente){
        return repository.save(cliente);
    }

    @Override
    public Cliente findByCpfCliente(String cpf) {
        verificaSeClienteExiste(cpf);
        Cliente cliente = repository.findByCpfCliente(cpf);
        cliente.getConsultasCliente().clear();
        cliente.setConsultasCliente(consultaRepository.
                findByClienteConsultaAndDataConsultaOrderByDataConsultaDesc(cliente, geraDataAtual()));
        return repository.findByCpfCliente(cpf);
    }

    private void verificaSeClienteExiste(String cpf) {
        if (repository.findByCpfCliente(cpf) == null) {
            throw new ResourceNotFoundException("Não existe cliente com esse CPF: " + cpf);
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
