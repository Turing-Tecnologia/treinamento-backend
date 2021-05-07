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


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "clinica")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Clinica implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_clinica")
    private long idClinica;

    @Column(name = "nome_Clinica", nullable = false)
    private String nomeClinica;

    @Column(name = "telefone", nullable = false)
    private String telefone;

    @Column(name = "email")
    private String email;

    @Column(name = "cnpj", nullable = false)
    private String cnpj;
    
    @OneToMany(mappedBy = "clinicaConsulta", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    private Set<Consulta> consultasMarcadas;

    @OneToMany(targetEntity = ConsultaRealizada.class, mappedBy = "clinicaConsultaRealizada", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    private Set<Consulta> consultasRealizadas;
}
