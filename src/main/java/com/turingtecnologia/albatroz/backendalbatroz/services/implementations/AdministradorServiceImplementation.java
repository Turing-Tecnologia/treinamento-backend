package com.turingtecnologia.albatroz.backendalbatroz.services.implementations;

import com.sendgrid.*;
import com.turingtecnologia.albatroz.backendalbatroz.exceptions.error.ResourceNotFoundException;
import com.turingtecnologia.albatroz.backendalbatroz.model.entities.Cliente;
import com.turingtecnologia.albatroz.backendalbatroz.model.entities.Consulta;
import com.turingtecnologia.albatroz.backendalbatroz.model.entities.ConsultaRealizada;
import com.turingtecnologia.albatroz.backendalbatroz.model.jpaRepositoy.ClienteRepository;
import com.turingtecnologia.albatroz.backendalbatroz.model.jpaRepositoy.ConsultaRealizadaRepository;
import com.turingtecnologia.albatroz.backendalbatroz.model.jpaRepositoy.ConsultaRepository;
import com.turingtecnologia.albatroz.backendalbatroz.services.interfaces.AdministradorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@AllArgsConstructor
public class AdministradorServiceImplementation implements AdministradorService {
    private final ConsultaRepository consultaRepository;
    private final ConsultaRealizadaRepository consultaRealizadaRepository;
    private final ClienteRepository clienteRepository;

    @Override
    public List<Cliente> findAllClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        for (Cliente cliente : clientes) {
            cliente.getConsultasRealizadasCliente().clear();
        }
        return clienteRepository.findAll();
    }

    @Override
    public void removeCliente(Cliente cliente) {
        cliente.setIdCliente(clienteRepository.
                findByCpfCliente(cliente.getCpfCliente()).getIdCliente());
        verificaClienteCPF(cliente.getCpfCliente());
        removeConsultasCliente(cliente);
        clienteRepository.delete(cliente);
    }

    @Override
    public Cliente alteraCliente(Cliente cliente) {
        verificaClienteCPF(cliente.getCpfCliente());
        cliente.setIdCliente(clienteRepository.findByCpfCliente(cliente.getCpfCliente()).getIdCliente());
        return clienteRepository.save(cliente);
    }

    @Override
    public Consulta marcaCheckIn(Long idConsulta) {
        verificaConsultaId(idConsulta);

        Consulta consulta = consultaRepository.findByIdConsulta(idConsulta);

        List<Consulta> consultaList = consultaRepository.findByDataConsultaOrderByIdConsulta(consulta.getDataConsulta());

        TimeZone timeZone = TimeZone.getTimeZone("America/Sao_Paulo");
        TimeZone.setDefault(timeZone);

        Calendar currentDateTime = Calendar.getInstance(timeZone);
        Date hora = new Date();
        hora.setTime(currentDateTime.getTimeInMillis());

        consulta.setCheckInConsulta(hora);

        for (Consulta c : consultaList) {
            if (c.getNumeroFichaConsulta() == consulta.getNumeroFichaConsulta() + 1) {
                sendEmail(clienteRepository.findByCpfCliente(c.getClienteConsulta().getCpfCliente()).getEmailCliente());
            }
        }
        return consultaRepository.save(consulta);
    }

    @Override
    public void marcaCheckOut(Long idConsulta) {
        verificaConsultaId(idConsulta);

        Consulta consulta = consultaRepository.findByIdConsulta(idConsulta);

        TimeZone timeZone = TimeZone.getTimeZone("Ameria/Sao_Paulo");
        TimeZone.setDefault(timeZone);
        
        Calendar currentDateTime = Calendar.getInstance(timeZone);

        Date hora = new Date();
        hora.setTime(currentDateTime.getTimeInMillis());

        consulta.setCheckOutConsulta(hora);
        ConsultaRealizada consultaRealizada = new ConsultaRealizada(consulta);
        consultaRealizadaRepository.save(consultaRealizada);
        consultaRepository.delete(consulta);
    }

    @Override
    public List<Consulta> findAllConsultas() {
        return consultaRepository.findAll();
    }

    @Override
    public List<ConsultaRealizada> findAllConsultasRealizadas() {
        return consultaRealizadaRepository.findAll();
    }

    @Override
    public List<Cliente> findConsultasPorData(String data) {
        Date d1;
        Calendar dataConsulta = Calendar.getInstance();
        try {
            d1 = new SimpleDateFormat("yyyy-MM-dd").parse(data);
            dataConsulta.setTime(d1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<Consulta> consultas = consultaRepository.findByDataConsultaOrderByIdConsulta(dataConsulta);
        List<Cliente> clientes = new ArrayList<>();
        for (int i = 0; i < consultas.size(); i++) {
            if (!(consultas.get(i).getClienteConsulta() == null)) {
                Cliente cliente = clienteRepository.findByCpfCliente(consultas.get(i).getClienteConsulta().getCpfCliente());
                clientes.add(cliente);
            }
            clientes.get(i).getConsultasRealizadasCliente().clear();
        }
        for (Cliente cliente : clientes) {
            cliente.getConsultasCliente().removeIf(consulta -> !consulta.getDataConsulta().equals(dataConsulta));
        }
        LinkedHashSet<Cliente> hashSet = new LinkedHashSet<>(clientes);
        return new ArrayList<>(hashSet);
    }

    @Override
    public List<Cliente> findConsultasDoDia() {
        Calendar dataConsulta = geraDataAtual();

        List<Consulta> consultas = consultaRepository.findByDataConsultaOrderByIdConsulta(dataConsulta);
        List<Cliente> clientes = new ArrayList<>();
        for (int i = 0; i < consultas.size(); i++) {
            if (!(consultas.get(i).getClienteConsulta() == null)) {
                Cliente cliente = clienteRepository.findByCpfCliente(consultas.get(i).getClienteConsulta().getCpfCliente());
                clientes.add(cliente);
            }
            clientes.get(i).getConsultasRealizadasCliente().clear();
        }
        for (Cliente cliente : clientes) {
            cliente.getConsultasCliente().removeIf(consulta -> !datasIguais(consulta.getDataConsulta(), dataConsulta));
        }
        LinkedHashSet<Cliente> hashSet = new LinkedHashSet<>(clientes);
        return new ArrayList<>(hashSet);
    }

    @Override
    public List<ConsultaRealizada> findConsultasRealizadasPorPeriodo(String dataInicial, String dataFinal) {
        Calendar data1 = Calendar.getInstance();
        Calendar data2 = Calendar.getInstance();
        try {
            Date d1 = new SimpleDateFormat("yyyy-MM-dd").parse(dataInicial);
            Date d2 = new SimpleDateFormat("yyyy-MM-dd").parse(dataFinal);
            data1.setTime(d1);
            data2.setTime(d2);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return consultaRealizadaRepository.findByDataConsultaRelizadaBetween(data1, data2);
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

    private void sendEmail(String email) {
        Email from = new Email("turing.tec.dev@gmail.com");
        Email to = new Email(email);
        Content content = new Content("text/plain", "Você é próximo da fila! Por favor, compareça à clínica, assim que possível, para aguardar o seu atendimento.");
        Mail mail = new Mail(from,
                "Situação da fila de atendimentos da Clínica Sorrir",
                to,
                content);
        SendGrid sg = new SendGrid(System.getenv("SENDGRID_KEY_ACCESS"));
        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void verificaConsultaId(long id) {
        if (consultaRepository.findByIdConsulta(id) == null) {
            throw new ResourceNotFoundException("Não existe consulta com esse ID = " + id);
        }
    }

    private void verificaClienteCPF(String cpf) {
        if (clienteRepository.findByCpfCliente(cpf) == null) {
            throw new ResourceNotFoundException("Não existe clinte com esse CPF = " + cpf);
        }
    }

    private void removeConsultasCliente(Cliente cliente) {
        List<Consulta> consultasMarcadas = consultaRepository.findByClienteConsulta(cliente);
        List<ConsultaRealizada> consultasRealizadas = consultaRealizadaRepository.findByClienteConsultaRealizada(cliente);
        for (ConsultaRealizada consultaRealizada : consultasRealizadas) {
            consultaRealizadaRepository.delete(consultaRealizada);
        }
        for (Consulta consulta : consultasMarcadas) {
            consultaRepository.delete(consulta);
        }
    }

    private Calendar geraDataAtual(){
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
