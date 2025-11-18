package application.aluno;

import java.time.LocalDate;

public record AlunoDTO(long id, String nome, String email, String telefone, LocalDate dataMatricula) {

    public AlunoDTO(Aluno dados) {
        this(dados.getId(), dados.getNome(), dados.getEmail(), dados.getTelefone(), dados.getDataMatricula());
    }
}
