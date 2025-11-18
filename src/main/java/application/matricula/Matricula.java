package application.matricula;

import application.aluno.Aluno;
import application.curso.Curso;

import java.time.LocalDate; // <-- Importação necessária

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "matriculas")
@Getter
@Setter
@NoArgsConstructor
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "data_matricula", nullable = false)
    private LocalDate dataMatricula;

    @Column(nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "aluno_id", nullable = false)
    private Aluno aluno;

    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;

    public Matricula(LocalDate dataMatricula, String status, Aluno aluno, Curso curso) {
        this.dataMatricula = dataMatricula;
        this.status = status;
        this.aluno = aluno;
        this.curso = curso;
    }

    public Matricula(MatriculaDTO dados) {
        this.id = dados.id();
        this.dataMatricula = dados.dataMatricula();
        this.status = dados.status();
        this.aluno = dados.aluno();
        this.curso = dados.curso();
    }

    public Matricula(MatriculaInsertDTO dados) {
        this.dataMatricula = dados.dataMatricula();
        this.status = dados.status();
        this.aluno = dados.aluno();
        this.curso = dados.curso();
    }
}
