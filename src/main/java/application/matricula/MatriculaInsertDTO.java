package application.matricula;

import application.aluno.Aluno;
import application.curso.Curso;
import java.time.LocalDate;

public record MatriculaInsertDTO(LocalDate dataMatricula, String status, Aluno aluno, Curso curso) {

    public MatriculaInsertDTO(Matricula dados) {
        this(dados.getDataMatricula(), dados.getStatus(), dados.getAluno(), dados.getCurso());
    }
}
