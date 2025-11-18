package application.aluno;

import java.time.LocalDate;

public record AlunoInsertDTO(String nome, String email, String telefone, LocalDate dataMatricula) {

    public AlunoInsertDTO(Aluno dados) {
        this(dados.getNome(), dados.getEmail(), dados.getTelefone(), dados.getDataMatricula());
    }
}
