package com.turingtecnologia.albatroz.backendalbatroz.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "consultas")
@Getter
@Setter
@NoArgsConstructor
public class Consulta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_consulta")
    private Long idConsulta;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cliente_consulta")
    private Cliente clienteConsulta;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_clinica_consulta")
    private Clinica clinicaConsulta;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_consulta", nullable = false)
    private Calendar dataConsulta;

    @JsonFormat(pattern = "HH:mm:ss")
    @Temporal(TemporalType.TIME)
    @Column(name = "check_in_consulta")
    private Date checkInConsulta;

    @Column(name = "numero_ficha_consulta")
    private int numeroFichaConsulta;

    @JsonFormat(pattern = "HH:mm:ss")
    @Temporal(TemporalType.TIME)
    @Column(name = "check_out_consulta")
    private Date checkOutConsulta;

    @Column(name = "especialidade_consulta", nullable = false)
    private String especialidadeConsulta;
}
