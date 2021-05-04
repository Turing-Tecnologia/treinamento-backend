package com.turingtecnologia.albatroz.backendalbatroz.model.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "clientes")
@Getter
@Setter
@NoArgsConstructor
public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_cliente")
    private long idCliente;

    @Column(name = "cpf_cliente", unique = true, nullable = false, length = 11)
    private String cpfCliente;

    @Column(name = "contato_cliente", nullable = false)
    private String contatoCliente;

    @Column(name = "nome_cliente", nullable = false)
    private String nomeCliente;

    @Column(name = "endereco_cliente", nullable = false)
    private String enderecoCliente;

    @Column(name = "email_cliente", nullable = false)
    private String emailCliente;

    @JsonManagedReference
    @OneToMany(mappedBy = "clienteConsulta", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    private Set<Consulta> consultasCliente;

    @OneToMany(targetEntity = ConsultaRealizada.class, mappedBy = "clienteConsultaRealizada", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    private Set<Consulta> consultasRealizadasCliente;

    @JsonManagedReference
    public Set<Consulta> getConsultasRealizadasCliente() {
        return consultasRealizadasCliente;
    }
}
