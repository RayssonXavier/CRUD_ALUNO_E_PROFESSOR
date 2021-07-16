package com.escola.escola.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.escola.escola.model.Aluno;
import com.escola.escola.model.SerieAluno;
import com.escola.escola.model.StatusAluno;
import com.escola.escola.model.Turno;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class RequisicaoAluno {
    
    @NotEmpty(message="O campo não pode ser vazio!")
    private String nome;
    @NotNull(message="A idade não pode ser vazia!")
    private Integer idade;
    private Turno turno;
    private SerieAluno serieAluno;
    private StatusAluno status;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dataNascimento = new Date();

    public Aluno toAluno(){
        Aluno aluno = new Aluno();
        aluno.setNome(this.nome);
        aluno.setIdade(this.idade);
        aluno.setTurno(this.turno);
        aluno.setSerieAluno(this.serieAluno);
        aluno.setStatus(this.status);
        aluno.setDataNascimento(this.dataNascimento);
        return aluno;
    }

    public Aluno toAluno(Aluno aluno){
        
        aluno.setNome(this.nome);
        aluno.setIdade(this.idade);
        aluno.setTurno(this.turno);
        aluno.setSerieAluno(this.serieAluno);
        aluno.setStatus(this.status);
        aluno.setDataNascimento(this.dataNascimento);
        return aluno;
    }

    public void fromAluno(Aluno aluno){
        this.nome = aluno.getNome();
        this.idade = aluno.getIdade();
        this.turno = aluno.getTurno();
        this.serieAluno = aluno.getSerieAluno();
        this.status = aluno.getStatus();
        this.dataNascimento = aluno.getDataNascimento();
    }    

}
