package application.aluno;

import java.time.LocalDate;

public record AlunoDTO(Long id, String nome, String email, String telefone, LocalDate dataMatricula) {
    public AlunoDTO(Aluno a) {
        this(a.getId(), a.getNome(), a.getEmail(), a.getTelefone(), a.getDataMatricula());
    }
}