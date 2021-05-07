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
@Table(name = "consultas_realizadas")
@Getter
@Setter
@NoArgsConstructor
public class ConsultaRealizada implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_consulta_realizada")
    private Long idConsultaRelizada;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente_consulta_realizada")
    private Cliente clienteConsultaRealizada;

    @JsonBackReference(value = "clinica-consulta")
    @ManyToOne
    @JoinColumn(name = "id_clinica_consulta_realizada")
    private Clinica clinicaConsultaRealizada;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_consulta_realizada", nullable = false)
    private Calendar dataConsultaRelizada;

    @JsonFormat(pattern = "HH:mm:ss")
    @Temporal(TemporalType.TIME)
    @Column(name = "check_in_consulta_realizada")
    private Date checkInConsultaRelizada;

    @Column(name = "numero_ficha_consulta_realizada")
    private int numeroFichaConsultaRelizada;

    @JsonFormat(pattern = "HH:mm:ss")
    @Temporal(TemporalType.TIME)
    @Column(name = "check_out_consulta_realizada")
    private Date checkOutConsultaRelizada;

    @Column(name = "especialidade_consulta_realizada", nullable = false)
    private String especialidadeConsultaRelizada;

    public ConsultaRealizada(Consulta consulta) {
        this.checkInConsultaRelizada = consulta.getCheckInConsulta();
        this.checkOutConsultaRelizada = consulta.getCheckOutConsulta();
        this.clienteConsultaRealizada = consulta.getClienteConsulta();
        this.dataConsultaRelizada = consulta.getDataConsulta();
        this.numeroFichaConsultaRelizada = consulta.getNumeroFichaConsulta();
        this.especialidadeConsultaRelizada = consulta.getEspecialidadeConsulta();
        this.clinicaConsultaRealizada = consulta.getClinicaConsulta();
    }

    public String getNomeClienteConsultaRealizada(){
        return clienteConsultaRealizada.getNomeCliente();
    }
}
