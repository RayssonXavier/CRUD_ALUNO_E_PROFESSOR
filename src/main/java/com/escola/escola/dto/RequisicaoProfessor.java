package com.escola.escola.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.escola.escola.model.Professor;
import com.escola.escola.model.StatusProfessor;

@Getter @Setter
@NoArgsConstructor
public class RequisicaoProfessor {
    @NotBlank(message = "O campo não pode está em branco!")
    @NotEmpty (message = "O campo não pode está vazio!")
    private String nome;
    private StatusProfessor status;

    @NotNull (message = "O campo não pode ser nulo!")
    @DecimalMin(value = "0.0", message = "O valor tem que está a cima de 0.0!")
    private BigDecimal salario;

    public Professor toProfessor(){
        Professor professor = new Professor();
        professor.setNome(this.nome);
        professor.setSalario(this.salario);
        professor.setStatus(this.status);
        return professor;
    }

    public void fromProfessor(Professor professor){
        this.nome = professor.getNome();
        this.salario = professor.getSalario();
        this.status = professor.getStatus();
    }

    public Professor toProfessor(Professor professor){
        professor.setNome(this.nome);
        professor.setSalario(this.salario);
        professor.setStatus(this.status);
        return professor;
    }
}
