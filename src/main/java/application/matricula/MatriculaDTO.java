package application.matricula;

import application.aluno.Aluno;
import application.curso.Curso;
import java.time.LocalDate;

public record MatriculaDTO(long id, LocalDate dataMatricula, String status, Aluno aluno, Curso curso) {

    public MatriculaDTO(Matricula dados) {
        this(dados.getId(), dados.getDataMatricula(), dados.getStatus(), dados.getAluno(), dados.getCurso());
    }
}
