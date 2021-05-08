package com.turingtecnologia.albatroz.backendalbatroz.model.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "clinicas")
@Getter
@Setter
@NoArgsConstructor
public class Clinica implements Serializable {
    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_clinica")
    private long idClinica;

    @Column(name = "cnpj_clinica", unique = true, nullable = false, length = 14)
    private String cnpjClinica;

    @Column(name = "nome_clinica", nullable = false)
    private String nomeClinica;

    @Column(name = "endereco_clinica", nullable = false)
    private String enderecoClinica;

    @Column(name = "email_clinica", nullable = false)
    private String emailClinica;

    @OneToMany(mappedBy = "clinicaConsulta", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    Set<Consulta> consultasClinica;

    @OneToMany(mappedBy = "clinicaConsultaRealizada", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    Set<ConsultaRealizada> consultasRealizadasClinica;
}
