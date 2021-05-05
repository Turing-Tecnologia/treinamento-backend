package com.turingtecnologia.albatroz.backendalbatroz.model.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "dentistas")
@Getter
@Setter
@NoArgsConstructor
public class Dentista implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_dentista")
    private long idDentista;

    @Column(name = "cpf_dentista", unique = true, nullable = false, length = 11)
    private String cpfDentista;

    @Column(name = "contato_dentista", nullable = false)
    private String contatoDentista;

    @Column(name = "nome_dentista", nullable = false)
    private String nomeDentista;

    @Column(name = "endereco_dentista", nullable = false)
    private String enderecoDentista;

    @Column(name = "email_dentista", nullable = false)
    private String emailDentista;
    
    @JsonManagedReference
    @OneToMany(mappedBy = "dentistaConsulta", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    private Set<Consulta> consultasDentista;

    @OneToMany(targetEntity = ConsultaRealizada.class, mappedBy = "dentistaConsultaRealizada", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    private Set<Consulta> consultasRealizadasDentista;

    @JsonManagedReference
    public Set<Consulta> getConsultasRealizadasDentista() {
        return consultasRealizadasDentista;
    }
}