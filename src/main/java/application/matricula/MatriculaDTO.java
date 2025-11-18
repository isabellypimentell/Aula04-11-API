package application.matricula;

import application.aluno.AlunoDTO;
import application.curso.CursoDTO;
import java.time.LocalDate;

public record MatriculaDTO(Long id, LocalDate dataMatricula, String status, AlunoDTO aluno, CursoDTO curso) {
    public MatriculaDTO(Matricula m) {
        this(m.getId(), m.getDataMatricula(), m.getStatus(), 
             new AlunoDTO(m.getAluno()), 
             new CursoDTO(m.getCurso()));
    }
}