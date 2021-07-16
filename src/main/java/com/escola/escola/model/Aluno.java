package com.escola.escola.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@Entity
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Integer idade;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Turno turno;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SerieAluno serieAluno;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusAluno status;

    @Temporal(TemporalType.DATE)
    private Date dataNascimento = new Date();
    
}
