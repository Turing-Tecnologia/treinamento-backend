package com.turingtecnologia.albatroz.backendalbatroz.services.implementations;

import com.turingtecnologia.albatroz.backendalbatroz.exceptions.error.ResourceNotAcceptableException;
import com.turingtecnologia.albatroz.backendalbatroz.exceptions.error.ResourceNotFoundException;
import com.turingtecnologia.albatroz.backendalbatroz.model.entities.Cliente;
import com.turingtecnologia.albatroz.backendalbatroz.model.entities.Clinica;
import com.turingtecnologia.albatroz.backendalbatroz.model.entities.Consulta;
import com.turingtecnologia.albatroz.backendalbatroz.model.entities.ConsultaRealizada;
import com.turingtecnologia.albatroz.backendalbatroz.model.jpaRepositoy.ClienteRepository;
import com.turingtecnologia.albatroz.backendalbatroz.model.jpaRepositoy.ClinicaRepository;
import com.turingtecnologia.albatroz.backendalbatroz.model.jpaRepositoy.ConsultaRealizadaRepository;
import com.turingtecnologia.albatroz.backendalbatroz.model.jpaRepositoy.ConsultaRepository;
import com.turingtecnologia.albatroz.backendalbatroz.services.interfaces.ConsultaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
@AllArgsConstructor
public class ConsultaServiceImplementation implements ConsultaService {
    private final ConsultaRepository consultaRepository;
    private final ConsultaRealizadaRepository consultaRealizadaRepository;
    private final ClienteRepository clienteRepository;
    private final ClinicaRepository clinicaRepository;

    @Override
    public Consulta addConsulta(Consulta consulta) {
        Cliente cliente = clienteRepository.findByCpfCliente(consulta.getClienteConsulta().getCpfCliente());
        Clinica clinica = clinicaRepository.findById(consulta.getClinicaConsulta().getIdClinica())
                                            .orElseThrow(() -> new ResourceNotAcceptableException("clinica não encontrada!"));
        consulta.setClinicaConsulta(clinica);
        consulta.setClienteConsulta(cliente);
        if (clienteJaAgendado(consulta)) {
            consulta.setNumeroFichaConsulta(getNumeroFicha(consulta.getDataConsulta()));
            return consultaRepository.save(consulta);
        } else if (!clienteJaAgendado(consulta)) {
            throw new ResourceNotAcceptableException("Cliente já tem essa consulta.");
        } else {
            throw new ResourceNotAcceptableException("Ops não é dia valido.");
        }
    }

    @Override
    public Consulta findByIdConsulta(Long id) {
        vericaSeConsultaExiste(id);
        return consultaRepository.findByIdConsulta(id);
    }

    @Override
    public Consulta alteraConsulta(Consulta consulta) {
        vericaSeConsultaExiste(consulta.getIdConsulta());
        Consulta consultaAntiga = consultaRepository.findByIdConsulta(consulta.getIdConsulta());
        if (!datasIguais(consulta.getDataConsulta(), consultaAntiga.getDataConsulta())) {
            consultaRepository.delete(consultaAntiga);
            deletarNumeroDaFicha(consultaAntiga.getDataConsulta());
            consulta.setIdConsulta(null);
            consulta.setNumeroFichaConsulta(getNumeroFicha(consulta.getDataConsulta()));
        }
        return consultaRepository.save(consulta);
    }

    /*@Override
    public List<InfoConsultaDTO> converter(Set<Consulta> consultas) {
        if(CollectionUtils.isEmpty(consultas)){
            return Collections.emptyList();
        }
        return consultas.stream()
                .map(consulta -> {
                    Clinica clinica = consulta.getClinicaConsulta();
                    Cliente cliente = consulta.getClienteConsulta();
                    InfoConsultaDTO dto = InfoConsultaDTO.builder()
                    .nomeClinica(clinica.getNomeClinica())
                    .nomeCliente(cliente.getNomeCliente())
                    .checkInConsulta(consulta.getCheckInConsulta())
                    .checkOutConsulta(consulta.getCheckOutConsulta())
                    .dataConsulta(consulta.getDataConsulta())
                    .especialidadeConsulta(consulta.getEspecialidadeConsulta())
                    .numeroFichaConsulta(consulta.getNumeroFichaConsulta())
                    .cpfCliente(cliente.getCpfCliente())
                    .contato(cliente.getContatoCliente())
                    .build();
                    return dto;
                }).collect(Collectors.toList());
    }*/

    @Override
    public void remove(Long id) {
        vericaSeConsultaExiste(id);
        Calendar data = consultaRepository.findByIdConsulta(id).getDataConsulta();
        consultaRepository.delete(consultaRepository.findByIdConsulta(id));
        deletarNumeroDaFicha(data);
    }

    private boolean clienteJaAgendado(Consulta consulta) {
        List<Consulta> consultaCliente = consultaRepository.findByDataConsultaAndClienteConsulta(consulta.getDataConsulta(), consulta.getClienteConsulta());
        return consultaCliente.size() <= 0;
    }

    private boolean datasIguais(Calendar data1, Calendar data2) {
        int dia1 = data1.get(Calendar.DAY_OF_MONTH);
        int dia2 = data2.get(Calendar.DAY_OF_MONTH);
        int mes1 = data1.get(Calendar.MONTH);
        int mes2 = data2.get(Calendar.MONTH);
        int ano1 = data1.get(Calendar.YEAR);
        int ano2 = data1.get(Calendar.YEAR);

        return (ano1 == ano2 && mes1 == mes2 && dia1 == dia2);
    }

    private void deletarNumeroDaFicha(Calendar data) {
        List<Consulta> consultas = consultaRepository.findByDataConsultaOrderByIdConsulta(data);
        for (int i = 0; i < consultas.size(); i++) {
            consultas.get(i).setNumeroFichaConsulta(i + 1);
        }
        consultaRepository.saveAll(consultas);
    }

    private boolean eDiaValido(Calendar data) {
        return (data.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) ||
                (data.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY);
    }

    private int getNumeroFicha(Calendar dataConsulta) {
        List<Consulta> consultas = consultaRepository.findByDataConsultaOrderByNumeroFichaConsulta(dataConsulta);
        List<ConsultaRealizada> consultasRealizadas = consultaRealizadaRepository.findByDataConsultaRelizadaOrderByNumeroFichaConsultaRelizada(dataConsulta);

        if (consultas.isEmpty() && consultasRealizadas.isEmpty()) {
            return 1;
        } else if (consultas.isEmpty() && !consultasRealizadas.isEmpty()) {
            return consultasRealizadas.get(consultasRealizadas.size() - 1).getNumeroFichaConsultaRelizada() + 1;
        } else {
            return consultas.get(consultas.size() - 1).getNumeroFichaConsulta() + 1;
        }
    }

    private void vericaSeConsultaExiste(long id) {
        if (consultaRepository.findByIdConsulta(id) == null) {
            throw new ResourceNotFoundException("Não existe consulta com esse ID: " + id);
        }
    }
}
